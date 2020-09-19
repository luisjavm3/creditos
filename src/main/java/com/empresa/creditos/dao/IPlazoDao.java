package com.empresa.creditos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresa.creditos.entity.credito.Plazo;

@Repository
public interface IPlazoDao extends JpaRepository<Plazo, Integer> {

	Plazo save(Plazo plazo);

	// Plazo findByPlazo(int plazo);

	Plazo findByPlazo(String plazo);

	List<Plazo> findAll();

}
