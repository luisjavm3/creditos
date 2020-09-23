package com.empresa.creditos.dao;

import java.util.List;

import com.empresa.creditos.entity.Cobro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICobroDao extends JpaRepository<Cobro, Integer>{
	
	Cobro save(Cobro cobro);
	
	// @EntityGraph(value = "graph.Cobro.clientes")
	Cobro findById(int id);
	
	@Query(value = "SELECT c FROM Cobro c LEFT JOIN FETCH c.creditos WHERE c.id = :id")
	Cobro findByIdWithCreditos(@Param("id") int id);
	
	@Query(value = "SELECT c FROM Cobro c LEFT JOIN FETCH c.liquidaciones WHERE c.id = :id")
	Cobro findByIdWithLiquidaciones(@Param("id") int id);
	
	List<Cobro> findAll();

}
