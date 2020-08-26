package com.empresa.creditos.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.empresa.creditos.entity.direccion.Direccion;

public interface IDireccionDao extends CrudRepository<Direccion, Integer>{
	
	Direccion save(Direccion direccion);
	
	void deleteById(int id);
	
	List<Direccion> findAll();

}
