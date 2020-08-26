package com.empresa.creditos.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.empresa.creditos.entity.Configuracion;

public interface IConfiguracionDao extends CrudRepository<Configuracion, Integer>{
	
	Configuracion save(Configuracion configuracion);
	
	void deleteById(int id);
	
	void delete(Configuracion configuracion);
	
	List<Configuracion> findAll();

}
