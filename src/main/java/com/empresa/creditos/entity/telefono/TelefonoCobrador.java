package com.empresa.creditos.entity.telefono;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.empresa.creditos.entity.Cobrador;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "telefonos_cobradores")
public class TelefonoCobrador implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	@Size(min = 10, max = 10, message = "El telefono debe tener 10 digitos")
	private String numero;

	@ManyToOne
	@JoinColumn(name = "cobrador_id", nullable = false)
	@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "telefonos" })
	private Cobrador cobrador;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Cobrador getCobrador() {
		return cobrador;
	}

	public void setCobrador(Cobrador cobrador) {
		this.cobrador = cobrador;
	}

	private static final long serialVersionUID = 1L;
}
