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

	public Plazo(int plazo, String descripcion) {
		this.plazo = plazo;
		this.descripcion = descripcion;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NaturalId
	@Column(unique = true, nullable = false) // Esto puede resultar redundate debido a la notacion NaturalId
	private int plazo;

	@Column(unique = true, nullable = false)
	private String descripcion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlazo() {
		return plazo;
	}

	public void setPlazo(int plazo) {
		this.plazo = plazo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	private static final long serialVersionUID = 1L;
}
