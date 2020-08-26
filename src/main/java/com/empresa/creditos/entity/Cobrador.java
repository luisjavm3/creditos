package com.empresa.creditos.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

import org.hibernate.annotations.NaturalId;

import com.empresa.creditos.entity.credito.Credito;
import com.empresa.creditos.entity.direccion.Direccion;
import com.empresa.creditos.entity.telefono.TelefonoCobrador;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

	@Column
	private String apodo;

//	#####

	@OneToOne
	@JoinColumn(name = "cobro_id", referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"cobrador"})
	private Cobro cobro;

	@OneToOne
	@JoinColumn(name = "direccion_id", nullable = false)
	private Direccion direccion;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cobrador")
	@JsonIgnoreProperties(value = { "cobrador" })
	private List<TelefonoCobrador> telefonos = new ArrayList<TelefonoCobrador>();

	@OneToMany(mappedBy = "cobrador")
	private List<Credito> creditos = new ArrayList<Credito>();

//	##########

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

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

//	@Override
//	public boolean equals(Object o) {
//
//		if (o == this)
//			return true;
//
//		if (!(o instanceof Cobrador) || o == null)
//			return false;
//
//		Cobrador that = (Cobrador) o;
//		
//		return Objects.equals(that.id, id);
//	}
//	
//	@Override
//	public int hashCode() {
//		return 31;
//	}

	private static final long serialVersionUID = 1L;
}
