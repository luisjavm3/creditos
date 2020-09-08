package com.empresa.creditos.dao;

import java.util.List;

import com.empresa.creditos.entity.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteDao extends JpaRepository<Cliente, Integer>{
	
	Cliente save(Cliente cliente);
	
	Cliente findById(int id);

	Cliente findByCedula(int cedula);

	void deleteById(int id);
	
	void delete(Cliente credito);
	
	List<Cliente> findAll();

}
