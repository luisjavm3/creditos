package com.empresa.creditos.service.direccion;

import java.util.List;

import com.empresa.creditos.entity.direccion.Direccion;

public interface IDireccionService {

	Direccion save(Direccion direccion);
	
	List<Direccion> findAll();
	
}
