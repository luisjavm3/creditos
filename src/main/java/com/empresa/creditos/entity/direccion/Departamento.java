package com.empresa.creditos.entity.direccion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "departamentos")
public class Departamento implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NaturalId
	private String departamento;

	@OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties(value = { "departamento" })
	private List<Municipio> municipios = new ArrayList<Municipio>();

	// ############## Helper Methods #####################

	public void addMunicipio(Municipio m) {
		m.setDepartamento(this);
		this.municipios.add(m);
	}

	public void removeMunicipio(Municipio m) {
		m.setDepartamento(null);
		this.municipios.remove(m);
	}

	// ############### Getters and Setters ###############33

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public List<Municipio> getMunicipios() {
		return municipios;
	}

	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}

	private static final long serialVersionUID = 1L;
}
