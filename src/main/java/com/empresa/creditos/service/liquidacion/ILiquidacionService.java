package com.empresa.creditos.service.liquidacion;

import java.util.List;

import com.empresa.creditos.entity.liquidacion.Liquidacion;

public interface ILiquidacionService {

	Liquidacion save(Liquidacion liquidacion);
	
	List<Liquidacion> findAll();
	
}
