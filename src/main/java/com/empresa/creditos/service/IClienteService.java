package com.empresa.creditos.service;

import java.util.List;

import com.empresa.creditos.entity.Cliente;

public interface IClienteService {

	Cliente save(Cliente cliente);
	
	Cliente findById(int id);

	Cliente findByCedula(String cedula);

	void deleteById(int id);
	
	void delete(Cliente credito);
	
	List<Cliente> findAll();
	
}
