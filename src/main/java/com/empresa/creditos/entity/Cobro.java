package com.empresa.creditos.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.NaturalId;

import com.empresa.creditos.entity.credito.Credito;
import com.empresa.creditos.entity.liquidacion.Liquidacion;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "cobros")
@NamedEntityGraph(name = "graph.Cobro.clientes", attributeNodes = @NamedAttributeNode(value = "clientes"))
public class Cobro implements Serializable {

	public Cobro() {
	}

	public Cobro(String nombre) {
		this.nombre = nombre;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NaturalId
	private String nombre;

	@Formula(value = "SELECT sum(c.saldo) FROM creditos c WHERE id = c.cobro_id")
	private Integer total;

//	#####

	@OneToOne(mappedBy = "cobro")
	@JsonIgnoreProperties(value = { "creditos", "cobro" })
	private Cobrador cobrador;

	@OneToMany(mappedBy = "cobro", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties(value = { "cobro", "credito" })
	private List<Cliente> clientes = new ArrayList<Cliente>();

	@OneToMany(mappedBy = "cobro", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties(value = { "cobro", "abonos", "cliente", "cobrador" })
	private List<Credito> creditos = new ArrayList<Credito>();

	@OneToMany(mappedBy = "cobro", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties(value = { "cobro", "abonos" })
	private List<Liquidacion> liquidaciones = new ArrayList<Liquidacion>();

//	##########

	public void addCliente(Cliente c) {
		this.clientes.add(c);
		c.setCobro(this);
	}

	public void addCredito(Credito c) {
		this.creditos.add(c);
		c.setCobro(this);
	}

	public void addLiquidacion(Liquidacion l) {
		this.liquidaciones.add(l);
		l.setCobro(this);
	}

	public List<Credito> getCreditos() {
		return creditos;
	}

	public List<Liquidacion> getLiquidaciones() {
		return liquidaciones;
	}

	public void setLiquidaciones(List<Liquidacion> liquidaciones) {
		this.liquidaciones = liquidaciones;
	}

	public void setCreditos(List<Credito> creditos) {
		this.creditos = creditos;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cobrador getCobrador() {
		return cobrador;
	}

	public void setCobrador(Cobrador cobrador) {
		this.cobrador = cobrador;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;

		if (!(o instanceof Cobro) || o == null)
			return false;

		Cobro that = (Cobro) o;

		return Objects.equals(that.nombre, nombre);
	}

	@Override
	public int hashCode() {

		return Objects.hash(nombre);
	}

	@Override
	public String toString() {

		return "id: " + id + " nombre: " + nombre;
	}

	private static final long serialVersionUID = 1L;
}
