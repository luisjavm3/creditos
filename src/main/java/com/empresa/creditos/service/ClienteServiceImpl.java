package com.empresa.creditos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.creditos.dao.IClienteDao;
import com.empresa.creditos.entity.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;

	@Override
	public Cliente save(Cliente cliente) {
		return this.clienteDao.save(cliente);
	}

	@Override
	public List<Cliente> findAll() {
		return this.clienteDao.findAll();
	}

	@Override
	public Cliente findById(int id) {
		return clienteDao.findById(id);
	}

	@Override
	public Cliente findByCedula(int cedula) {
		return clienteDao.findByCedula(cedula);
	}

	@Override
	public void deleteById(int id) {
		clienteDao.deleteById(id);
	}

	@Override
	public void delete(Cliente credito) {
		clienteDao.delete(credito);
	}

}
