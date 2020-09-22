package com.empresa.creditos.entity.liquidacion;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.empresa.creditos.entity.credito.Credito;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

@Entity
@Table(name = "abonos")
public class Abono implements Serializable {

	@EmbeddedId
	@JsonUnwrapped
	private AbonoId id = new AbonoId();

	@Column(nullable = false)
	private int abono;

	@Column(name = "created_at", nullable = false)
	private LocalDate createdAt;

	// ====================== Entity's Relationships ======================

	// @JsonIgnoreProperties(value = { "abonos", "hibernateLazyInitializer" })
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("liquidacionId")
	@JsonIgnore
	private Liquidacion liquidacion;

	// @JsonIgnoreProperties(value = { "cobro", "cliente", "abonos", "cobrador",
	// "hibernateLazyInitializer" })
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("creditoId")
	@JsonIgnore
	private Credito credito;

	// ====================== Constructors =======================

	public Abono() {
	}

	public Abono(Credito credito, int abono) {

		if (credito.getSaldo() - abono < 0) {
			return;
		}

		this.credito = credito;
		this.abono = abono;
	}

	public Abono(AbonoId id, int abono) {
		this.id = id;
		this.abono = abono;
	}

	// ====================== Entity's Life Cycle ===================

	@PrePersist
	public void prePersist() {

		this.createdAt = LocalDate.now();

		int saldoMenosAbono = credito.getSaldo() - abono;

		if (saldoMenosAbono == 0) {

			int posicionAntesDeActualizar = credito.getPosicionEnRuta();

			credito.setSaldo(0);
			credito.setCancelado(true);
			credito.setFechaCancelado(new Date());
			credito.setPosicionEnRuta(null);

			// Esta lista de creditos contiene al credito actualizado, pero en su posicion
			// de ruta original
			List<Credito> creditos = liquidacion.getCobro().getCreditos();

			for (int i = posicionAntesDeActualizar; i < creditos.size(); i++) {
				Credito c = creditos.get(i);
				c.setPosicionEnRuta(c.getPosicionEnRuta() - 1);
			}

		} else if (saldoMenosAbono > 0) {
			credito.setSaldo(saldoMenosAbono);
		} else {
			// do-nothing
		}

	}

	// ====================== Helper Methods ====================

	// ====================== Getters and Setters ====================

	// @JsonAnyGetter
	public AbonoId getId() {
		return id;
	}

	public int getAbono() {
		return abono;
	}

	public Liquidacion getLiquidacion() {
		return liquidacion;
	}

	public Credito getCredito() {
		return credito;
	}

	public void setId(AbonoId id) {
		this.id = id;
	}

	public void setLiquidacion(Liquidacion liquidacion) {
		this.liquidacion = liquidacion;
	}

	public void setCredito(Credito credito) {
		this.credito = credito;
	}

	public void setAbono(int abono) {
		if (this.credito.getSaldo() - abono < 0) {
			throw new IllegalArgumentException();
		}
		this.abono = abono;
	}

	public LocalDate getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
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
