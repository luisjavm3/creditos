package com.empresa.creditos.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.empresa.creditos.entity.direccion.Barrio;

public interface IBarrioDao extends CrudRepository<Barrio, Integer>{
	
	Barrio save(Barrio barrio);
	
	void deleteById(int id);
	
	void delete(Barrio barrio);
	
	List<Barrio> findAll();

}
