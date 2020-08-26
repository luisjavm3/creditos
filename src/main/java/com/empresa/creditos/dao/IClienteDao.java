package com.empresa.creditos.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.empresa.creditos.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Integer>{
	
	Cliente save(Cliente cliente);
	
	void deleteById(int id);
	
	void delete(Cliente credito);
	
	List<Cliente> findAll();

}
