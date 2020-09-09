package com.empresa.creditos.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.empresa.creditos.entity.credito.Credito;
import com.empresa.creditos.entity.liquidacion.Liquidacion;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "cobros")
public class Cobro implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NaturalId
	private String nombre;

	// // @Transient
	// @Formula(value = "SELECT COUNT(c.cobro_id) FROM creditos c WHERE c.cobro_id = id")
	// private int numeroDeCreditos;

	@Formula(value = "SELECT SUM(c.saldo) FROM creditos c WHERE c.cobro_id =  id")
	private Integer total;

	// ====================== Entity's Relationships ======================

	@OneToOne(mappedBy = "cobro", cascade = { CascadeType.PERSIST, CascadeType.REFRESH,
			CascadeType.MERGE }, orphanRemoval = false, fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = { "creditos", "cobro" })
	private Cobrador cobrador;

	@OneToMany(mappedBy = "cobro", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties(value = { "cobro", "credito" })
	private List<Cliente> clientes = new ArrayList<Cliente>();

	@OneToMany(mappedBy = "cobro", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties(value = { "cobro", "abonos", "cliente", "cobrador" })
	@OrderBy("posicionEnRuta")
	private List<Credito> creditos = new ArrayList<Credito>();

	@OneToMany(mappedBy = "cobro", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties(value = { "cobro", "abonos" })
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

	// ====================== Getters and Setters ======================

	public Cobrador getCobrador() {
		return this.cobrador;
	}

	public Integer getTotal() {
		if (this.total == null)
			return 0;
		return this.total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Cliente> getClientes() {
		return this.clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Credito> getCreditos() {
		return this.creditos;
	}

	public void setCreditos(List<Credito> creditos) {
		this.creditos = creditos;
	}

	public List<Liquidacion> getLiquidaciones() {
		return this.liquidaciones;
	}

	public void setLiquidaciones(List<Liquidacion> liquidaciones) {
		this.liquidaciones = liquidaciones;
	}

	public int getNumeroDeCreditos() {
		return this.numeroDeCreditos;
	}

	public void setNumeroDeCreditos(int numeroDeCreditos) {
		this.numeroDeCreditos = numeroDeCreditos;
	}

	@Override
	public String toString() {
		return "{" + " id='" + getId() + "'" + ", nombre='" + getNombre() + "'" + ", numeroDeCreditos='"
				+ getNumeroDeCreditos() + "'" + ", total='" + getTotal() + "'" + "}";
	}

	private static final long serialVersionUID = 1L;
}
