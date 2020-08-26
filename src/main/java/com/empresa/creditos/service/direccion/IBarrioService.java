package com.empresa.creditos.service.direccion;

import java.util.List;

import com.empresa.creditos.entity.direccion.Barrio;

public interface IBarrioService {

	Barrio save(Barrio barrio);
	
	List<Barrio> findAll();
	
}
