package com.empresa.creditos.entity.credito;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContexts;
import javax.persistence.PostLoad;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.empresa.creditos.entity.Cliente;
import com.empresa.creditos.entity.Cobrador;
import com.empresa.creditos.entity.Cobro;
import com.empresa.creditos.entity.liquidacion.Abono;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "creditos")
// @Table(name = "creditos", uniqueConstraints = @UniqueConstraint(columnNames =
// { "cobro_id", "posicion_ruta" }))
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

	@Transient
	@JsonIgnoreProperties(value = { "fechaCredito", "fechaCancelado", "credito", "cancelado", "cuota", "totalAbonos",
			"saldo", "boleta", "total", "cobro", "cliente", "abonos", "cobrador", "interes", "perioricidad", "plazo",
			"saveCredito" })
	private transient Credito savedCredito;

	// ====================== Entity's Relationships ======================

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cobro_id", nullable = false)
	@JsonIgnoreProperties(value = { "creditos", "cobrador", "clientes", "liquidaciones", "hibernateLazyInitializer" })
	private Cobro cobro;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id", referencedColumnName = "id", nullable = false)
	@JsonIgnoreProperties(value = { "cobro", "credito", "hibernateLazyInitializer" })
	private Cliente cliente;

	@OneToMany(mappedBy = "credito", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties(value = { "id", "credito" })
	private List<Abono> abonos = new ArrayList<Abono>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cobrador_id", nullable = false)
	@JsonIgnoreProperties(value = { "cobro", "creditos", "hibernateLazyInitializer" })
	private Cobrador cobrador;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "interes_id", nullable = false)
	@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
	private Interes interes;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "perioricidad_id", nullable = false)
	@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
	private Perioricidad perioricidad;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "plazo_id", nullable = false)
	@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
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

	@PostLoad
	public void postLoad() {

		// System.out.println("=== POSTLOAD === : " + this.toString());
		setSavedCredito(new Credito(this));

	}

	@PrePersist
	public void prePersist() {

		fechaCredito = new Date();
		cancelado = false;
		total = credito * (100 + interes.getInteres()) / 100;
		cuota = total / plazo.getPlazo();
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

	@PostUpdate
	public void postUpdate() {

		if (cancelado) {

			final int numeroCreditos = cobro.getNumeroDeCreditos();
			final int rutaAnterior = this.getSavedCredito().getPosicionEnRuta();

			if (rutaAnterior <= numeroCreditos) {

				final List<Credito> creditos = cobro.getCreditos();
				final int puntoDeInicio = rutaAnterior - 1;

				for (int i = puntoDeInicio; i < numeroCreditos; i++) {

					final Credito credito = creditos.get(i);
					credito.setPosicionEnRuta(credito.getPosicionEnRuta() - 1);

					System.out.println(credito.toString());
				}

			}

		}

	}

	// ====================== Helper Methods ======================

	public void addAbono(final Abono a) {
		a.setCredito(this);
		this.abonos.add(a);
	}

	public void removeAbono(final Abono a) {
		a.setCredito(null);
		this.abonos.remove(a);
	}

	// ====================== Getters and Setters ======================

	public void setCobrador(final Cobrador c) {
		this.cobrador = c;
	}

	public Cobrador getCobrador() {
		return cobrador;
	}

	public int getTotalAbonos() {
		if (this.totalAbonos == null)
			return 0;
		return totalAbonos;
	}

	public void setTotalAbonos(final Integer totalAbonos) {
		this.totalAbonos = totalAbonos;
	}

	public List<Abono> getAbonos() {
		return abonos;
	}

	public void setAbonos(final List<Abono> abonos) {
		this.abonos = abonos;
	}

	public boolean isCancelado() {
		return cancelado;
	}

	public void setCancelado(final boolean cancelado) {
		this.cancelado = cancelado;
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public Date getFechaCredito() {
		return fechaCredito;
	}

	public void setFechaCredito(final Date fechaCredito) {
		this.fechaCredito = fechaCredito;
	}

	public Date getFechaCancelado() {
		return fechaCancelado;
	}

	public void setFechaCancelado(final Date fechaCancelado) {
		this.fechaCancelado = fechaCancelado;
	}

	public int getCredito() {
		return credito;
	}

	public void setCredito(final int credito) {
		this.credito = credito;
	}

	public int getPosicionEnRuta() {
		if (this.posicionEnRuta == null)
			return 0;
		return this.posicionEnRuta;
	}

	public void setPosicionEnRuta(final Integer posicionEnRuta) {
		this.posicionEnRuta = posicionEnRuta;
	}

	public float getCuota() {
		return cuota;
	}

	public void setCuota(final float cuota) {
		this.cuota = cuota;
	}

	public int getBoleta() {
		return boleta;
	}

	public void setBoleta(final int boleta) {
		this.boleta = boleta;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(final int total) {
		this.total = total;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(final Cliente cliente) {
		this.cliente = cliente;
	}

	public Cobro getCobro() {
		return cobro;
	}

	public void setCobro(final Cobro cobro) {
		this.cobro = cobro;
	}

	public Interes getInteres() {
		return interes;
	}

	public void setInteres(final Interes interes) {
		this.interes = interes;
	}

	public Perioricidad getPerioricidad() {
		return perioricidad;
	}

	public void setPerioricidad(final Perioricidad perioricidad) {
		this.perioricidad = perioricidad;
	}

	public Plazo getPlazo() {
		return plazo;
	}

	public void setPlazo(final Plazo plazo) {
		this.plazo = plazo;
	}

	public Integer getSaldo() {
		if (this.saldo == null)
			return 0;
		return this.saldo;
	}

	public void setSaldo(final Integer saldo) {
		this.saldo = saldo;
	}

	public Credito getSavedCredito() {
		return this.savedCredito;
	}

	public void setSavedCredito(final Credito credito) {
		this.savedCredito = credito;
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
