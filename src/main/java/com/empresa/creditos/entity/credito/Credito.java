package com.empresa.creditos.entity.credito;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.empresa.creditos.entity.Cliente;
import com.empresa.creditos.entity.Cobrador;
import com.empresa.creditos.entity.Cobro;
import com.empresa.creditos.entity.liquidacion.Abono;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "creditos")
@Where(clause = "cancelado = false")
public class Credito implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// This field must be: updatable = false
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_credito", nullable = false)
	private Date fechaCredito;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_cancelado")
	private Date fechaCancelado;

	@Column(nullable = false)
	private int credito;

	@Column(name = "posicion_ruta")
	private Integer posicionEnRuta;

	private boolean cancelado;

	private float cuota;

	@Formula(value = "SELECT SUM(a.abono) FROM abonos a WHERE a.credito_id = id")
	private Integer totalAbonos;

	private Integer saldo;

	private int boleta;

	private int total;

	// ====================== Entity's Relationships ======================

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cobro_id", nullable = false)
	private Cobro cobro;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id", referencedColumnName = "id", nullable = false)
	private Cliente cliente;

	@OneToMany(mappedBy = "credito", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Abono> abonos = new ArrayList<Abono>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cobrador_id", nullable = false)
	private Cobrador cobrador;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "interes_id", nullable = false)
	private Interes interes;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "perioricidad_id", nullable = false)
	private Perioricidad perioricidad;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "plazo_id", nullable = false)
	private Plazo plazo;

	// ====================== Constructors =======================

	public Credito() {
	}

	public Credito(final Credito credito) {
		this.id = credito.getId();
		this.posicionEnRuta = credito.getPosicionEnRuta();
	}

	public Credito(final int credito, final int posicionEnRuta, final int boleta, final Interes interes,
			final Perioricidad perioricidad, final Plazo plazo) {
		this.credito = credito;
		this.posicionEnRuta = posicionEnRuta;
		this.boleta = boleta;
		this.interes = interes;
		this.perioricidad = perioricidad;
		this.plazo = plazo;
	}

	public Credito(final int credito, final int boleta, final Interes interes, final Perioricidad perioricidad,
			final Plazo plazo) {
		this.credito = credito;
		this.boleta = boleta;
		this.interes = interes;
		this.perioricidad = perioricidad;
		this.plazo = plazo;
		this.posicionEnRuta = 0;
	}

	// ====================== Entity's Life Cycle ===================

	@PrePersist
	public void prePersist() {

		fechaCredito = new Date();
		cancelado = false;
		total = credito * (100 + interes.getInteres()) / 100;
		cuota = total / plazo.getDias();
		saldo = total;

		final int numeroDeCreditos = cobro.getNumeroDeCreditos();

		// Si el cobro tiene creditos activos
		if (numeroDeCreditos != 0) {

			if (posicionEnRuta <= numeroDeCreditos && posicionEnRuta > 0) {

				final List<Credito> creditos = cobro.getCreditos();
				Collections.reverse(creditos);

				final int recorrerHasta = numeroDeCreditos - posicionEnRuta + 1;

				for (int i = 0; i < recorrerHasta; i++) {
					final Credito credito = creditos.get(i);
					credito.setPosicionEnRuta(credito.getPosicionEnRuta() + 1);
				}

			} else {
				// si posicionEnRuta viene inicializada en cero o es mayor al # de creditos
				// activos del cobro, se le asigna la ultima posicion de la ruta
				posicionEnRuta = numeroDeCreditos + 1;
			}

		} else {
			// Si el cobro no tiene creditos, asigna al credito la posicion 1 en ruta
			posicionEnRuta = 1;
		}

	}

	// ====================== Helper Methods ======================

	public void addAbono(Abono a) {
		a.setCredito(this);
		this.abonos.add(a);
	}

	public void removeAbono(Abono a) {
		a.setCredito(null);
		this.abonos.remove(a);
	}

	// ====================== Getters ======================

	public int getId() {
		return id;
	}

	public Date getFechaCredito() {
		return fechaCredito;
	}

	public Date getFechaCancelado() {
		return fechaCancelado;
	}

	public int getCredito() {
		return credito;
	}

	public int getPosicionEnRuta() {
		if (this.posicionEnRuta == null)
			return 0;
		return this.posicionEnRuta;
	}

	public boolean getCancelado() {
		return this.cancelado;
	}

	public boolean isCancelado() {
		return cancelado;
	}

	public float getCuota() {
		return cuota;
	}

	public int getTotalAbonos() {
		if (this.totalAbonos == null)
			return 0;
		return totalAbonos;
	}

	public Integer getSaldo() {
		if (this.saldo == null)
			return 0;
		return this.saldo;
	}

	public int getBoleta() {
		return boleta;
	}

	public int getTotal() {
		return total;
	}

	// -----

	@JsonIgnore
	public Cobro getCobro() {
		return cobro;
	}

	// @JsonIgnoreProperties(value = { "cobro", "credito",
	// "hibernateLazyInitializer" })
	@JsonIgnore
	public Cliente getCliente() {
		return cliente;
	}

	@JsonIgnore
	public List<Abono> getAbonos() {
		return abonos;
	}

	// @JsonIgnoreProperties(value = { "cobro", "creditos",
	// "hibernateLazyInitializer" })
	@JsonIgnore
	public Cobrador getCobrador() {
		return cobrador;
	}

	@JsonUnwrapped
	@JsonIgnoreProperties(value = { "id", "hibernateLazyInitializer" })
	public Interes getInteres() {
		return interes;
	}

	@JsonUnwrapped
	@JsonIgnoreProperties(value = { "id", "dias", "hibernateLazyInitializer" })
	public Perioricidad getPerioricidad() {
		return perioricidad;
	}

	@JsonUnwrapped
	@JsonIgnoreProperties(value = { "id", "dias", "hibernateLazyInitializer" })
	public Plazo getPlazo() {
		return plazo;
	}

	// ====================== Setters ======================

	public void setCobrador(Cobrador c) {
		this.cobrador = c;
	}

	public void setTotalAbonos(final Integer totalAbonos) {
		this.totalAbonos = totalAbonos;
	}

	public void setAbonos(final List<Abono> abonos) {
		this.abonos = abonos;
	}

	public void setCancelado(final boolean cancelado) {
		this.cancelado = cancelado;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public void setFechaCredito(final Date fechaCredito) {
		this.fechaCredito = fechaCredito;
	}

	public void setFechaCancelado(final Date fechaCancelado) {
		this.fechaCancelado = fechaCancelado;
	}

	public void setCredito(final int credito) {
		this.credito = credito;
	}

	public void setPosicionEnRuta(final Integer posicionEnRuta) {
		this.posicionEnRuta = posicionEnRuta;
	}

	public void setCuota(final float cuota) {
		this.cuota = cuota;
	}

	public void setBoleta(final int boleta) {
		this.boleta = boleta;
	}

	public void setTotal(final int total) {
		this.total = total;
	}

	public void setCliente(final Cliente cliente) {
		this.cliente = cliente;
	}

	public void setCobro(final Cobro cobro) {
		this.cobro = cobro;
	}

	public void setInteres(final Interes interes) {
		this.interes = interes;
	}

	public void setPerioricidad(final Perioricidad perioricidad) {
		this.perioricidad = perioricidad;
	}

	public void setPlazo(final Plazo plazo) {
		this.plazo = plazo;
	}

	public void setSaldo(final Integer saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "{" + " id='" + getId() + "'" + ", fechaCredito='" + getFechaCredito() + "'" + ", fechaCancelado='"
				+ getFechaCancelado() + "'" + ", credito='" + getCredito() + "'" + ", posicionEnRuta='"
				+ getPosicionEnRuta() + "'" + ", cancelado='" + isCancelado() + "'" + ", cuota='" + getCuota() + "'"
				+ ", saldo='" + getSaldo() + "'" + ", boleta='" + getBoleta() + "'" + ", total='" + getTotal() + "'"
				+ "}";
	}

	private static final long serialVersionUID = 1L;
}
