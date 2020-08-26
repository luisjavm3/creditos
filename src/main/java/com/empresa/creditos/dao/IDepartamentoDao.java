package com.empresa.creditos.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.empresa.creditos.entity.direccion.Departamento;

public interface IDepartamentoDao extends CrudRepository<Departamento, Integer>{
	
	Departamento save(Departamento departamento);
	
	Departamento findByNombre(String nombre);
	
	List<Departamento> findAll();

}
