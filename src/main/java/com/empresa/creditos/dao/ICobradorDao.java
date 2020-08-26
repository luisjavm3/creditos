package com.empresa.creditos.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.empresa.creditos.entity.Cobrador;

public interface ICobradorDao extends CrudRepository<Cobrador, Integer>{
	
	Cobrador save(Cobrador cobrador);
	
	void deleteById(int id);
	
	void delete(Cobrador cobrador);
	
	List<Cobrador> findAll();

}
