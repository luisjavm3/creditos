package com.empresa.creditos.entity.direccion;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

@Entity
@Table(name = "direcciones")
public class Direccion implements Serializable {

	public Direccion() {
	}

	public Direccion(Departamento departamento, Municipio municipio, Barrio barrio, String direccion) {
		this.departamento = departamento;
		this.municipio = municipio;
		this.barrio = barrio;
		this.direccion = direccion;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "departamento_id", nullable = false)
	// @JsonUnwrapped
	// @JsonIgnoreProperties(value = { "id", "municipios",
	// "hibernateLazyInitializer" })
	@JsonIgnoreProperties(value = { "municipios", "hibernateLazyInitializer" })
	private Departamento departamento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "municipio_id", nullable = false)
	// @JsonUnwrapped
	// @JsonIgnoreProperties(value = {"id", "departamento", "barrios",
	// "hibernateLazyInitializer" })
	@JsonIgnoreProperties(value = { "departamento", "barrios", "hibernateLazyInitializer" })
	private Municipio municipio;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "barrio_id", nullable = true)
	// @JsonUnwrapped
	// @JsonIgnoreProperties(value = { "id", "municipio", "hibernateLazyInitializer"
	// })
	@JsonIgnoreProperties(value = { "municipio", "hibernateLazyInitializer" })
	private Barrio barrio;

	@Column(nullable = false)
	private String direccion;

	// ################# Helper Methods #################

	// #################### Getters and Setters ############

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public Barrio getBarrio() {
		return barrio;
	}

	public void setBarrio(Barrio barrio) {
		this.barrio = barrio;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "{" + " id='" + getId() + "'" + ", departamento='" + getDepartamento() + "'" + ", municipio='"
				+ getMunicipio() + "'" + ", barrio='" + getBarrio() + "'" + ", direccion='" + getDireccion() + "'"
				+ "}";
	}

	private static final long serialVersionUID = 1L;
}
