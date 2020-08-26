package com.empresa.creditos.service;

import java.util.List;

import com.empresa.creditos.entity.Cobro;

public interface ICobroService {

	Cobro save(Cobro cobro);
	
	Cobro findById(int id);
	
	Cobro findByIdWithCreditos(int id);
	
	Cobro findByIdWithLiquidaciones(int id);
	
	List<Cobro> findAll();
	
}
