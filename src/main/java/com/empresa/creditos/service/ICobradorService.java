package com.empresa.creditos.service;

import java.util.List;

import com.empresa.creditos.entity.Cobrador;

public interface ICobradorService {

	Cobrador save(Cobrador cobrador);
	
	List<Cobrador> findAll();
	
}
