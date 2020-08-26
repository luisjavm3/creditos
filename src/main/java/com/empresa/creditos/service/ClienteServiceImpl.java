package com.empresa.creditos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.creditos.dao.IClienteDao;
import com.empresa.creditos.entity.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService{

	@Autowired
	private IClienteDao clienteDao;
	
	@Override
	public Cliente save(Cliente cliente) {
		
		return this.clienteDao.save(cliente);
	}
	
	@Override
	public List<Cliente> findAll(){
		
		return this.clienteDao.findAll();
	}
	
}
