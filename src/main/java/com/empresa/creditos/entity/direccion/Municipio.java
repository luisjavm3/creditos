package com.empresa.creditos.entity.direccion;

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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "municipios")
public class Municipio implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String municipio;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "departamento_id", nullable = false)
	@JsonIgnoreProperties(value = { "municipios", "hibernateLazyInitializer" })
	private Departamento departamento;

	@OneToMany(mappedBy = "municipio", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties(value = { "municipio" })
	private List<Barrio> barrios = new ArrayList<>();

	// ============== Contructors ===================

	public Municipio() {
	}

	public Municipio(String nombre) {
		this.municipio = nombre;
	}
	// ########## Helper Methods ###################

	public void addBarrio(Barrio b) {
		b.setMunicipio(this);
		this.barrios.add(b);
	}

	public void removeBarrios(Barrio b) {
		b.setMunicipio(null);
		this.barrios.remove(b);
	}

	// ################# Getters and Setters ################

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Barrio> getBarrios() {
		return barrios;
	}

	public void setBarrios(List<Barrio> barrios) {
		this.barrios = barrios;
	}

	@Override
	public String toString() {
		return "{" + " id='" + getId() + "'" + ", municipio='" + getMunicipio() + "'" + "}";
	}

	private static final long serialVersionUID = 1L;
}
