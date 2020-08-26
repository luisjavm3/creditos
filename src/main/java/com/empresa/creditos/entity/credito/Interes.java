package com.empresa.creditos.entity.credito;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "intereses")
public class Interes implements Serializable {

	public Interes() {
	}

	public Interes(int interes) {
		this.interes = interes;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NaturalId
//	@Column(unique = true, nullable = false)
	private int interes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getInteres() {
		return interes;
	}

	public void setInteres(int interes) {
		this.interes = interes;
	}

	private static final long serialVersionUID = 1L;
}
