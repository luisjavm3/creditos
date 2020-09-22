package com.empresa.creditos.service.direccion;

import java.util.List;

import com.empresa.creditos.entity.direccion.Departamento;

public interface IDepartamentoService {

	Departamento save(Departamento departamento);
	
	Departamento findByDepartamento(String nombre);
	
	List<Departamento> findAll();
	
}
