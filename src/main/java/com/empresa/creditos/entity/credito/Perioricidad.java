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

	public Perioricidad(int dias, String descripcion) {
		this.dias = dias;
		this.perioricidad = descripcion;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NaturalId
	private String perioricidad;

	@Column(unique = true, nullable = false)
	private int dias;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDias() {
		return this.dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}

	public String getPerioricidad() {
		return this.perioricidad;
	}

	public void setPerioricidad(String perioricidad) {
		this.perioricidad = perioricidad;
	}

	private static final long serialVersionUID = 1L;
}
