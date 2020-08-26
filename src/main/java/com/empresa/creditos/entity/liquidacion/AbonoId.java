package com.empresa.creditos.entity.liquidacion;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AbonoId implements Serializable {

	@Column(name = "liquidacion_id")
	private int liquidacionId;

	@Column(name = "credito_id")
	private int creditoId;

	public int getLiquidacionId() {
		return liquidacionId;
	}

	public void setLiquidacionId(int liquidacionId) {
		this.liquidacionId = liquidacionId;
	}

	public int getCreditoId() {
		return creditoId;
	}

	public void setCreditoId(int creditoId) {
		this.creditoId = creditoId;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		AbonoId that = (AbonoId) o;

		return Objects.equals(liquidacionId, that.liquidacionId) && Objects.equals(creditoId, that.creditoId);
	}

	@Override
	public int hashCode() {

		return Objects.hash(liquidacionId, creditoId);
	}

	private static final long serialVersionUID = 1L;
}
