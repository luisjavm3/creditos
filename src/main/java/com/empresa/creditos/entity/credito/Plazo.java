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
@Table(name = "plazos")
public class Plazo implements Serializable {

	public Plazo() {
	}

	public Plazo(int dias, String descripcion) {
		this.dias = dias;
		this.plazo = descripcion;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NaturalId
	private String plazo;

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

	public String getPlazo() {
		return plazo;
	}

	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	private static final long serialVersionUID = 1L;
}
