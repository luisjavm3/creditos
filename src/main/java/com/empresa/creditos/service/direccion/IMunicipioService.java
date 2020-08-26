package com.empresa.creditos.service.direccion;

import java.util.List;

import com.empresa.creditos.entity.direccion.Municipio;

public interface IMunicipioService {

	Municipio save(Municipio municipio);
	
	List<Municipio> findAll();
	
}
