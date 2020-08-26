package com.empresa.creditos.service.credito;

import java.util.List;

import com.empresa.creditos.entity.credito.Perioricidad;

public interface IPerioricidadService {

	Perioricidad save(Perioricidad perioricidad);
	
	Perioricidad findByPerioricidad(int perioricidad);
	
	Perioricidad findByDescripcion(String perioricidad);
	
	List<Perioricidad> findAll();
	
}
