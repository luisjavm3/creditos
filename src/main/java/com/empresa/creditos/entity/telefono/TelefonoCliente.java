package com.empresa.creditos.entity.telefono;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.empresa.creditos.entity.Cliente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "telefonos_clientes")
public class TelefonoCliente implements Serializable {

	@Id
	@GeneratedValue()
	private int id;

	@Size(min = 10, max = 10, message = "El telefon debe tener 10 numeros")
	private String numero;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id", nullable = false)
	@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "telefonos" })
	private Cliente cliente;

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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	private static final long serialVersionUID = 1L;
}
