package com.empresa.creditos.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.empresa.creditos.entity.credito.Credito;
import com.empresa.creditos.entity.direccion.Direccion;
import com.empresa.creditos.entity.telefono.TelefonoCliente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	public Cliente() {
	}

	public Cliente(int cedula, String nombres, String apellidos, String apodo, Cobro cobro) {
		this.cedula = cedula;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.apodo = apodo;
		this.cobro = cobro;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NaturalId
	@Column(updatable = false, nullable = false)
	private int cedula;

	@Column(nullable = false)
	private String nombres;

	@Column(nullable = false)
	private String apellidos;

	@Column
	private String apodo;

//	#####

	@ManyToOne
	@JoinColumn(name = "cobro_id", referencedColumnName = "id", nullable = false)
	@JsonIgnoreProperties(value = { "clientes", "liquidaciones", "creditos" })
	private Cobro cobro;

	@OneToOne(mappedBy = "cliente")
	private Credito credito;

	@OneToOne
	@JoinColumn(name = "direccion_id", referencedColumnName = "id", nullable = false)
	private Direccion direccion;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties(value = { "cliente" })
	private List<TelefonoCliente> telefonos = new ArrayList<TelefonoCliente>();

//	########################################################################################

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	
	public Direccion getDireccion() {
		return direccion;
	}

	public List<TelefonoCliente> getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(List<TelefonoCliente> telefonos) {
		this.telefonos = telefonos;
	}

	public Credito getCredito() {
		return credito;
	}

	public void setCredito(Credito credito) {
		this.credito = credito;
	}

	public Cobro getCobro() {
		return cobro;
	}

	public void setCobro(Cobro cobro) {
		this.cobro = cobro;
//		cobro.addCliente(this);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;

		if (!(o instanceof Cliente) || o == null)
			return false;

		Cliente that = (Cliente) o;
		
		return Objects.equals(that.cedula, cedula);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cedula);
	}
	
	@Override
	public String toString() {
		return ""+id+" "+nombres;
	}

	private static final long serialVersionUID = 1L;
}
