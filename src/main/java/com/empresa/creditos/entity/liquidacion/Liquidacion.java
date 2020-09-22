package com.empresa.creditos.entity.liquidacion;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.empresa.creditos.entity.Cobro;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.Formula;

@Entity
@Table(name = "liquidaciones", uniqueConstraints = @UniqueConstraint(columnNames = { "fecha_liquidacion", "cobro_id" }))
public class Liquidacion implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_liquidacion", nullable = false, updatable = false)
	private Date fechaLiquidacion;

	@Column(nullable = false)
	private int efectivo;

	@Column(nullable = false)
	private int base;

	@Formula(value = "SELECT SUM(a.abono) FROM abonos a WHERE a.liquidacion_id = id")
	private Integer cobrado;

	@Formula(value = "SELECT SUM(c.boleta) FROM creditos c WHERE c.fecha_credito = fecha_liquidacion AND c.cobro_id = cobro_id")
	private Integer totalBoletas;

	// @Formula(value = "SELECT SUM(c.credito) FROM creditos c WHERE c.fecha_credito
	// = fecha_liquidacion AND c.cobro_id = cobro_id")
	@Formula(value = "SELECT SUM(c.credito) FROM creditos c WHERE c.fecha_credito = fecha_liquidacion AND c.cobro_id = cobro_id")
	private Integer totalCreditos;

	@Column(nullable = false)
	private int gastos;

	@Column(nullable = true)
	private String nota;

	// @JsonIgnore
	@Transient
	private int resultado;

	// ====================== Entity's Relationships ======================

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cobro_id", referencedColumnName = "id", nullable = false)
	// @JsonIgnoreProperties(value = { "clientes", "creditos", "liquidaciones", "hibernateLazyInitializer" })
	@JsonIgnore
	private Cobro cobro;

	@OneToMany(mappedBy = "liquidacion", cascade = CascadeType.ALL, orphanRemoval = true)
	// @JsonIgnoreProperties(value = { "id", "liquidacion" })
	// @JsonIgnore
	private List<Abono> abonos = new ArrayList<Abono>();

	// ====================== Constructors =======================

	public Liquidacion() {
	}

	public Liquidacion(int efectivo, int base, int gastos, String nota) {
		this.efectivo = efectivo;
		this.base = base;
		this.gastos = gastos;
		this.nota = nota;
	}

	public Liquidacion(Date fechaLiquidacion, int efectivo, int base, int gastos, String nota) {
		this.fechaLiquidacion = fechaLiquidacion;
		this.efectivo = efectivo;
		this.base = base;
		this.gastos = gastos;
		this.nota = nota;
	}

	// ====================== Entity's Life Cycle ===================

	// @PrePersist
	// public void init() {
	// this.fechaLiquidacion = new Date();
	// }

	// ====================== Helper Methods ======================

	public void addAbono(Abono a) {
		a.setLiquidacion(this);
		// a.setCredito(credito);
		this.abonos.add(a);
	}

	public void removeAbono(Abono a) {
		a.setLiquidacion(null);
		this.abonos.remove(a);
	}

	// ====================== Getters and Setters ======================

	public int getCobrado() {
		if (cobrado == null)
			return 0;
		return cobrado;
	}

	public void setCobrado(int cobrado) {
		this.cobrado = cobrado;
	}

	public int getTotalBoletas() {
		if (totalBoletas == null)
			return 0;
		return totalBoletas;
	}

	public void setTotalBoletas(int totalBoletas) {
		this.totalBoletas = totalBoletas;
	}

	public int getTotalCreditos() {
		if (this.totalCreditos == null)
			return 0;
		return totalCreditos;
	}

	public void setTotalCreditos(int totalCreditos) {
		this.totalCreditos = totalCreditos;
	}

	public int getResultado() {
		this.resultado = getEfectivo() - getCobrado() - getBase() - getTotalBoletas() + getTotalCreditos()
				+ getGastos();
		return this.resultado;
	}

	public void setResultado(int resultado) {
		this.resultado = resultado;
	}

	public Cobro getCobro() {
		return cobro;
	}

	public void setCobro(Cobro cobro) {
		this.cobro = cobro;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaLiquidacion() {
		return fechaLiquidacion;
	}

	public void setFechaLiquidacion(Date fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}

	public int getEfectivo() {
		return efectivo;
	}

	public void setEfectivo(int efectivo) {
		this.efectivo = efectivo;
	}

	public int getBase() {
		return base;
	}

	public void setBase(int base) {
		this.base = base;
	}

	public int getGastos() {
		return gastos;
	}

	public void setGastos(int gastos) {
		this.gastos = gastos;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public List<Abono> getAbonos() {
		return abonos;
	}

	public void setAbonos(List<Abono> abonos) {
		this.abonos = abonos;
	}

	private static final long serialVersionUID = 1L;
}
