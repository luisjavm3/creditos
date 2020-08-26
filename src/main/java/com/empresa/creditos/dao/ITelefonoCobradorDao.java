package com.empresa.creditos.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.empresa.creditos.entity.telefono.TelefonoCobrador;

public interface ITelefonoCobradorDao extends CrudRepository<TelefonoCobrador, Integer>{
	
	TelefonoCobrador save(TelefonoCobrador telefonoCobrador);
	
	void delete(TelefonoCobrador telefonoCobrador);
	
	void deleteById(int id);
	
	List<TelefonoCobrador> findAll();

}
