package com.empresa.creditos.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.empresa.creditos.entity.telefono.TelefonoCliente;

public interface ITelefonoClienteDao extends CrudRepository<TelefonoCliente, Integer>{
	
	TelefonoCliente save(TelefonoCliente telefonoCliente);
	
	void deleteById(int id);
	
	void delete(TelefonoCliente telefonoCliente);
	
	List<TelefonoCliente> findAll();

}
