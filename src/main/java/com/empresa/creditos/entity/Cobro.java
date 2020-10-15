package com.empresa.creditos.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.empresa.creditos.entity.credito.Credito;
import com.empresa.creditos.entity.liquidacion.Liquidacion;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "cobros")
public class Cobro implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "El nombre del cobro no debe ser Nulo ni Vacío")
	@Size(min = 1, max = 30, message = "La longitud del nombre del cobro debe estar entre 1 y 30")
	@NaturalId
	@Column(nullable = false, unique = true)
	private String nombre;

	@Formula(value = "SELECT COUNT(c.cobro_id) FROM creditos c WHERE c.cobro_id = id AND c.cancelado = 0")
	private int numeroDeCreditos;

	@Formula(value = "SELECT SUM(c.saldo) FROM creditos c WHERE c.cobro_id =  id")
	private Integer total;

	// ====================== Entity's Relationships ======================

	// @JsonIgnoreProperties(value = { "cobro", "creditos" })
	@OneToOne(mappedBy = "cobro", cascade = { CascadeType.PERSIST, CascadeType.REFRESH,
			CascadeType.MERGE }, orphanRemoval = false, fetch = FetchType.LAZY)
	@JsonIgnore
	private Cobrador cobrador;

	// @JsonIgnoreProperties(value = { "cobro" })
	@OneToMany(mappedBy = "cobro", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Cliente> clientes = new ArrayList<Cliente>();

	// @JsonIgnoreProperties(value = { "cobro", "cobrador", "abonos" })
	@OneToMany(mappedBy = "cobro", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("posicionEnRuta")
	@JsonIgnore
	private List<Credito> creditos = new ArrayList<Credito>();

	// @JsonIgnoreProperties(value = { "cobro" })
	@OneToMany(mappedBy = "cobro", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Liquidacion> liquidaciones = new ArrayList<Liquidacion>();

	// ====================== Constructors =======================

	public Cobro() {
	}

	public Cobro(String nombre) {
		this.nombre = nombre;
	}

	// ====================== Entity's Life Cycle ===================

	// ====================== Helper Methods ======================

	public void setCobrador(Cobrador c) {
		if (c == null) {
			if (this.cobrador != null) {
				this.cobrador.setCobro(null);
			}
		} else {
			c.setCobro(this);
		}
		this.cobrador = c;
	}

	public void addCliente(Cliente c) {
		c.setCobro(this);
		this.clientes.add(c);
	}

	// public void addCredito(Credito c) {
	// 	this.creditos.add(c);
	// 	c.getCliente().setCredito(c);
	// }

	public void removeCliente(Cliente c) {
		this.clientes.remove(c);
		c.setCobro(null);
	}

	public void addLiquidacion(Liquidacion l) {
		this.liquidaciones.add(l);
		l.setCobro(this);
	}

	public void removeLiquidacion(Liquidacion l) {
		this.liquidaciones.remove(l);
		l.setCobro(null);
	}

	// ====================== Getters ======================

	public int getId() {
		return this.id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public int getNumeroDeCreditos() {
		return this.numeroDeCreditos;
	}

	public Integer getTotal() {
		if (this.total == null)
			return 0;
		return this.total;
	}

	public Cobrador getCobrador() {
		return this.cobrador;
	}

	public List<Cliente> getClientes() {
		return this.clientes;
	}

	public List<Credito> getCreditos() {
		return this.creditos;
	}

	public List<Liquidacion> getLiquidaciones() {
		return this.liquidaciones;
	}

	// ====================== Setters ======================

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNumeroDeCreditos(int numeroDeCreditos) {
		this.numeroDeCreditos = numeroDeCreditos;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	// SetCobrador is a helper method

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public void setCreditos(List<Credito> creditos) {
		this.creditos = creditos;
	}

	public void setLiquidaciones(List<Liquidacion> liquidaciones) {
		this.liquidaciones = liquidaciones;
	}

	@Override
	public String toString() {
		return "{" + " id='" + getId() + "'" + ", nombre='" + getNombre() + "'" + ", numeroDeCreditos='"
				+ getNumeroDeCreditos() + "'" + ", total='" + getTotal() + "'" + "}";
	}

	private static final long serialVersionUID = 1L;
}
