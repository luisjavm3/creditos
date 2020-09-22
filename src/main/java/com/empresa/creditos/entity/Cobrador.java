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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.empresa.creditos.entity.credito.Credito;
import com.empresa.creditos.entity.direccion.Direccion;
import com.empresa.creditos.entity.telefono.TelefonoCobrador;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "cobradores")
public class Cobrador implements Serializable {

	public Cobrador() {
	}

	public Cobrador(int cedula, String nombres, String apellidos, String apodo) {
		this.cedula = cedula;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.apodo = apodo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NaturalId
	private int cedula;

	@Column(nullable = false)
	private String nombres;

	@Column(nullable = false)
	private String apellidos;

	private String apodo;

	// #####

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cobro_id", referencedColumnName = "id")
	// @JsonIgnoreProperties(value = { "cobrador", "clientes", "creditos",
	// "liquidaciones", "hibernateLazyInitializer" })
	@JsonIgnore
	private Cobro cobro;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "direccion_id", nullable = false)
	@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
	private Direccion direccion;

	@OneToMany(mappedBy = "cobrador", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties(value = { "cobrador" })
	private List<TelefonoCobrador> telefonos = new ArrayList<TelefonoCobrador>();

	@OneToMany(mappedBy = "cobrador")
	// @JsonIgnoreProperties(value = { "cobro", "abonos", "cobrador" })
	@JsonIgnore
	private List<Credito> creditos = new ArrayList<Credito>();

	// ##################### Helper Methods ###########################

	public void setDireccion(Direccion d) {
		this.direccion = d;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void addTelefono(TelefonoCobrador t) {
		this.telefonos.add(t);
		t.setCobrador(this);
	}

	public void removeTelefono(TelefonoCobrador t) {
		this.telefonos.remove(t);
		t.setCobrador(null);
	}

	// ############################################

	public List<TelefonoCobrador> getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(List<TelefonoCobrador> telefonos) {
		this.telefonos = telefonos;
	}

	public List<Credito> getCreditos() {
		return creditos;
	}

	public void setCreditos(List<Credito> creditos) {
		this.creditos = creditos;
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
