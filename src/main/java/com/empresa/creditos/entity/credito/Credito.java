package com.empresa.creditos.entity.credito;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Where;

import com.empresa.creditos.entity.Cliente;
import com.empresa.creditos.entity.Cobrador;
import com.empresa.creditos.entity.Cobro;
import com.empresa.creditos.entity.liquidacion.Abono;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "creditos")
@Where(clause = "cancelado = false")
public class Credito implements Serializable {

	public Credito() {
	}

	public Credito(Cliente cliente, Cobrador cobrador, int credito, Interes interes, Perioricidad perioricidad,
			Plazo plazo) {
		this.cliente = cliente;
		this.cobrador = cobrador;
		this.credito = credito;
		this.interes = interes;
		this.perioricidad = perioricidad;
		this.plazo = plazo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_prestamo", nullable = false)
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

	// @Formula(value = "SELECT sum(a.abono) FROM abonos a WHERE a.credito_id = id")
	// private Integer saldo;

	private int boleta;

	private int total;

	// #####

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

	// ############## Entity's Life Cycle ###############

	@PrePersist
	public void asignarValoresAutomaticamente() {
		this.fechaCredito = new Date();
		this.fechaCancelado = null;
		this.cancelado = false;
		this.total = this.credito * (100 + this.interes.getInteres()) / 100;
		// this.saldo = total;
		this.cuota = this.total / this.plazo.getPlazo();
	}

	// ############### Helper Methods #################

	public void addAbono(Abono a) {
		a.setCredito(this);
		this.abonos.add(a);
	}

	public void removeAbono(Abono a) {
		a.setCredito(null);
		this.abonos.remove(a);
	}

	// ################# Getters and Setters #################

	public void setCobrador(Cobrador c) {
		// c.getCreditos().add(this);
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

	// public Integer getSaldo() {
	// return saldo;
	// }

	// public void setSaldo(Integer saldo) {
	// this.saldo = saldo;
	// }

	private static final long serialVersionUID = 1L;
}
