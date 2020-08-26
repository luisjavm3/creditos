package com.empresa.creditos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.creditos.entity.credito.Plazo;

public interface IPlazoDao extends JpaRepository<Plazo, Integer> {

	Plazo save(Plazo plazo);

	Plazo findByPlazo(int plazo);

	Plazo findByDescripcion(String descripcion);

	List<Plazo> findAll();

}
