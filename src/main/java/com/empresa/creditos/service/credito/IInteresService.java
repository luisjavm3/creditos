package com.empresa.creditos.service.credito;

import java.util.List;

import com.empresa.creditos.entity.credito.Interes;

public interface IInteresService {

	Interes save(Interes interes);
	
	Interes findByInteres(int interes);
	
	List<Interes> findAll();
	
}
