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

	public Municipio() {
	}

	public Municipio(Departamento departamento, String nombre) {
		this.departamento = departamento;
		this.nombre = nombre;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String nombre;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "departamento_id", nullable = false)
	@JsonIgnoreProperties(value = { "municipios", "hibernateLazyInitializer" })
	private Departamento departamento;

	@OneToMany(mappedBy = "municipio", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties(value = { "municipio" })
	private List<Barrio> barrios = new ArrayList<>();

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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	// @Override
	// public boolean equals(Object o) {

	// if (o == this)
	// return true;

	// if (!(o instanceof Municipio) || o == null)
	// return false;

	// Municipio that = (Municipio) o;

	// return Objects.equals(that.id, id);
	// }

	// @Override
	// public int hashCode() {

	// return 31;
	// }

	private static final long serialVersionUID = 1L;
}
