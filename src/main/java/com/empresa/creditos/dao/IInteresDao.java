package com.empresa.creditos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.creditos.entity.credito.Interes;

public interface IInteresDao extends JpaRepository<Interes, Integer>{
	
	Interes save(Interes interes);
	
	Interes findByInteres(int interes);
	
	List<Interes> findAll();

}
