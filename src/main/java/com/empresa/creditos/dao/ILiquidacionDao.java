package com.empresa.creditos.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.empresa.creditos.entity.liquidacion.Liquidacion;

public interface ILiquidacionDao extends CrudRepository<Liquidacion, Integer>{

	Liquidacion save(Liquidacion liquidacion);
		
	List<Liquidacion> findAll();
	
}
