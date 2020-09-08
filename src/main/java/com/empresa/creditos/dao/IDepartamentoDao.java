package com.empresa.creditos.dao;

import java.util.List;

import com.empresa.creditos.entity.direccion.Departamento;

import org.springframework.data.repository.CrudRepository;

public interface IDepartamentoDao extends CrudRepository<Departamento, Integer>{
	
	Departamento save(Departamento departamento);
	
	Departamento findByNombre(String nombre);
	
	List<Departamento> findAll();

}
