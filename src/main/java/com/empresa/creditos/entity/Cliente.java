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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.empresa.creditos.entity.credito.Credito;
import com.empresa.creditos.entity.direccion.Direccion;
import com.empresa.creditos.entity.telefono.TelefonoCliente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NaturalId
	private int cedula;

	@Column(nullable = false)
	private String nombres;

	@Column(nullable = false)
	private String apellidos;

	@Column
	private String apodo;

	// ====================== Entity's Relationships ================

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cobro_id", referencedColumnName = "id", nullable = false)
	@JsonIgnoreProperties(value = { "clientes", "liquidaciones", "creditos", "hibernateLazyInitializer" })
	private Cobro cobro;

	@OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = { "cobro", "cliente" })
	private Credito credito;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "direccion_id", referencedColumnName = "id", nullable = false)
	@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
	private Direccion direccion;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties(value = { "cliente" })
	private List<TelefonoCliente> telefonos = new ArrayList<TelefonoCliente>();

	// ====================== Constructors ======================

	public Cliente() {
	}

	public Cliente(int cedula, String nombres, String apellidos, String apodo) {
		this.cedula = cedula;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.apodo = apodo;
	}

	// ====================== Entity's Life Cycle ===================

	// ====================== Helper Methods ======================

	public void setCredito(Credito cr) {
		if (cr == null) {
			if (this.credito != null) {
				this.credito.setCliente(null);
			}
		} else {
			cr.setCliente(this);
			// adding new features
			cr.setCobro(this.cobro);
			cr.setCobrador(this.cobro.getCobrador());
		}
		this.credito = cr;
	}

	public void addTelefono(TelefonoCliente t) {
		t.setCliente(this);
		this.telefonos.add(t);
	}

	public void removeTelefono(TelefonoCliente t) {
		t.setCliente(null);
		this.telefonos.remove(t);
	}

	// ====================== Getters and Setters ======================

	public Credito getCredito() {
		return credito;
	}

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

	public Cobro getCobro() {
		return cobro;
	}

	public void setCobro(Cobro cobro) {
		this.cobro = cobro;
		// cobro.addCliente(this);
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

	private static final long serialVersionUID = 1L;
}
