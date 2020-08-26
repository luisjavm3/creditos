package com.empresa.creditos.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.empresa.creditos.entity.direccion.Municipio;

public interface IMunicipioDao extends CrudRepository<Municipio, Integer>{
	
	Municipio save(Municipio municipio);
	
	void delete(Municipio municipio);
	
	void deleteById(int id);
	
	List<Municipio> findAll();

}
