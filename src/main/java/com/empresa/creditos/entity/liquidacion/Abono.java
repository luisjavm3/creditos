package com.empresa.creditos.entity.liquidacion;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.empresa.creditos.entity.credito.Credito;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "abonos")
public class Abono implements Serializable {

	public Abono() {
	}

	public Abono(Liquidacion liquidacion, Credito credito, int abono) {
		this.liquidacion = liquidacion;
		this.credito = credito;
		this.abono = abono;
	}

	public Abono(int abono) {
		super();
		this.abono = abono;
	}

	@EmbeddedId
	private AbonoId id = new AbonoId();

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("liquidacionId")
	@JsonIgnoreProperties(value = { "cobro", "abonos", "hibernateLazyInitializer" })
	private Liquidacion liquidacion;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("creditoId")
	@JsonIgnoreProperties(value = { "cobro", "cliente", "abonos", "cobrador", "hibernateLazyInitializer" })
	private Credito credito;

	@Column(nullable = false)
	private int abono;

	// ############# Entity's life cycle #################

	// @PrePersist
	// public void prePersist() {
	// }

	// ############## Helper Methods ####################

	// ################# Getters and Setters ################

	public AbonoId getId() {
		return id;
	}

	public void setId(AbonoId id) {
		this.id = id;
	}

	public Liquidacion getLiquidacion() {
		return liquidacion;
	}

	public void setLiquidacion(Liquidacion liquidacion) {
		this.liquidacion = liquidacion;
	}

	public Credito getCredito() {
		return credito;
	}

	public void setCredito(Credito credito) {
		this.credito = credito;
	}

	public int getAbono() {
		return abono;
	}

	public void setAbono(int abono) {
		this.abono = abono;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		Abono that = (Abono) o;

		return Objects.equals(liquidacion, that.liquidacion) && Objects.equals(credito, that.credito);
	}

	@Override
	public int hashCode() {
		return Objects.hash(liquidacion, credito);
	}

	private static final long serialVersionUID = 1L;
}
