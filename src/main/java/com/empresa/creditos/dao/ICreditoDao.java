package com.empresa.creditos.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.empresa.creditos.entity.credito.Credito;

public interface ICreditoDao extends CrudRepository<Credito, Integer> {

	Credito save(Credito credito);

	Credito findById(int id);

	void deleteById(int id);

	void delete(Credito credito);

	List<Credito> findAll();

}
