package com.empresa.creditos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.creditos.entity.credito.Perioricidad;

public interface IPerioricidadDao extends JpaRepository<Perioricidad, Integer>{

	Perioricidad save(Perioricidad perioricidad);
	
	Perioricidad findByPerioricidad(int perioricidad);
	
	Perioricidad findByDescripcion(String perioricidad);
	
	List<Perioricidad> findAll();
	
}
