package com.empresa.creditos.entity.liquidacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.empresa.creditos.entity.Cobro;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "liquidaciones")
public class Liquidacion implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "fecha_liquidacion")
	private Date fechaLiquidacion;

	@Column
	private int efectivo;

	@Column
	private int base;

	@Formula(value = "select sum(a.abono) from abonos a where id = a.liquidacion_id")
	private Integer cobrado;

//	@Formula(value = "SELECT sum()")
	private int totalBoletas;

	private int totalCreditos;

	@Column(nullable = true)
	private int gastos;

	@Column(nullable = true)
	private String nota;

	private int resultado;

//	#####

	@ManyToOne
	@JoinColumn(name = "cobro_id", referencedColumnName = "id")
	@JsonIgnoreProperties(value = { "clientes", "creditos", "liquidaciones" })
	private Cobro cobro;

	@OneToMany(mappedBy = "liquidacion", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties(value = { "id", "liquidacion" })
	private List<Abono> abonos = new ArrayList<Abono>();

//	##########

	@PrePersist
	public void init() {
		this.fechaLiquidacion = new Date();
	}

	public void addAbono(Abono abono) {
		this.abonos.add(abono);
		abono.setLiquidacion(this);
	}
	
//	###############
	
	public int getResultado() {
		return resultado;
//		return efectivo - cobrado - base - totalBoletas + totalCreditos + gastos;
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

	public int getCobrado() {
		return cobrado;
	}

	public void setCobrado(int cobrado) {
		this.cobrado = cobrado;
	}

	public int getBase() {
		return base;
	}

	public void setBase(int base) {
		this.base = base;
	}

	public int getTotalBoletas() {
		return totalBoletas;
	}

	public void setTotalBoletas(int totalBoletas) {
		this.totalBoletas = totalBoletas;
	}

	public int getTotalCreditos() {
		return totalCreditos;
	}

	public void setTotalCreditos(int totalCreditos) {
		this.totalCreditos = totalCreditos;
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

	@Override
	public String toString() {
		return "liquidacion_id: "+id+" cobro_id "+cobro.getId()+" fecha_liquidacion: "+fechaLiquidacion;
	}
	
	private static final long serialVersionUID = 1L;
}
