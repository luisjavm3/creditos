package com.empresa.creditos.service;

import java.util.List;

import com.empresa.creditos.entity.Cliente;

public interface IClienteService {

	Cliente save(Cliente cliente);
	
	List<Cliente> findAll();
	
}
