package com.empresa.creditos.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "configuraciones")
public class Configuracion implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String datoDePrueba;

	public String getDatoDePrueba() {
		return datoDePrueba;
	}

	public void setDatoDePrueba(String datoDePrueba) {
		this.datoDePrueba = datoDePrueba;
	}

	private static final long serialVersionUID = 1L;
}
