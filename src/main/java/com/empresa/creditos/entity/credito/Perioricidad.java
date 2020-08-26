package com.empresa.creditos.entity.credito;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "perioricidades")
public class Perioricidad implements Serializable {

	public Perioricidad() {
	}

	public Perioricidad(int perioricidad, String descripcion) {
		this.perioricidad = perioricidad;
		this.descripcion = descripcion;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NaturalId
	private int perioricidad;

	@Column(unique = true, nullable = false)
	private String descripcion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPerioricidad() {
		return perioricidad;
	}

	public void setPerioricidad(int perioricidad) {
		this.perioricidad = perioricidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	private static final long serialVersionUID = 1L;
}
