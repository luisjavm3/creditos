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
import javax.persistence.UniqueConstraint;

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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_cancelado")
	private Date fechaCancelado;

	@Column(nullable = false)
	private int credito;

	@Column(name = "posicion_ruta")
	private int posicionEnRuta;

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

	public Credito(int credito, int posicionEnRuta, int boleta, Interes interes, Perioricidad perioricidad,
			Plazo plazo) {
		this.credito = credito;
		this.posicionEnRuta = posicionEnRuta;
		this.boleta = boleta;
		this.interes = interes;
		this.perioricidad = perioricidad;
		this.plazo = plazo;
	}

	public Credito(int credito, int boleta, Interes interes, Perioricidad perioricidad, Plazo plazo) {
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
		cuota = total / plazo.getPlazo();
		saldo = total;

		int numeroDeCreditos = cobro.getNumeroDeCreditos();

		// Si el cobro tiene creditos activos
		if (numeroDeCreditos != 0) {

			if (posicionEnRuta <= numeroDeCreditos && posicionEnRuta > 0) {

				List<Credito> creditos = cobro.getCreditos();
				Collections.reverse(creditos);

				int recorrerHasta = numeroDeCreditos - posicionEnRuta + 1;

				for (int i = 0; i < recorrerHasta; i++) {
					Credito credito = creditos.get(i);
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

	// ====================== Getters and Setters ======================

	public void setCobrador(Cobrador c) {
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

	public void setTotalAbonos(Integer totalAbonos) {
		this.totalAbonos = totalAbonos;
	}

	public List<Abono> getAbonos() {
		return abonos;
	}

	public void setAbonos(List<Abono> abonos) {
		this.abonos = abonos;
	}

	public boolean isCancelado() {
		return cancelado;
	}

	public void setCancelado(boolean cancelado) {
		this.cancelado = cancelado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaCredito() {
		return fechaCredito;
	}

	public void setFechaCredito(Date fechaCredito) {
		this.fechaCredito = fechaCredito;
	}

	public Date getFechaCancelado() {
		return fechaCancelado;
	}

	public void setFechaCancelado(Date fechaCancelado) {
		this.fechaCancelado = fechaCancelado;
	}

	public int getCredito() {
		return credito;
	}

	public void setCredito(int credito) {
		this.credito = credito;
	}

	public int getPosicionEnRuta() {
		return posicionEnRuta;
	}

	public void setPosicionEnRuta(int posicionEnRuta) {
		this.posicionEnRuta = posicionEnRuta;
	}

	public float getCuota() {
		return cuota;
	}

	public void setCuota(float cuota) {
		this.cuota = cuota;
	}

	public int getBoleta() {
		return boleta;
	}

	public void setBoleta(int boleta) {
		this.boleta = boleta;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cobro getCobro() {
		return cobro;
	}

	public void setCobro(Cobro cobro) {
		this.cobro = cobro;
	}

	public Interes getInteres() {
		return interes;
	}

	public void setInteres(Interes interes) {
		this.interes = interes;
	}

	public Perioricidad getPerioricidad() {
		return perioricidad;
	}

	public void setPerioricidad(Perioricidad perioricidad) {
		this.perioricidad = perioricidad;
	}

	public Plazo getPlazo() {
		return plazo;
	}

	public void setPlazo(Plazo plazo) {
		this.plazo = plazo;
	}

	public Integer getSaldo() {
		if (this.saldo == null)
			return 0;
		return this.saldo;
	}

	public void setSaldo(Integer saldo) {
		this.saldo = saldo;
	}

	private static final long serialVersionUID = 1L;
}
