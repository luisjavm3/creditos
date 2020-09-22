package com.empresa.creditos.service.direccion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.creditos.dao.IDepartamentoDao;
import com.empresa.creditos.entity.direccion.Departamento;

@Service
public class DepartamentoServiceImpl implements IDepartamentoService{

	@Autowired
	private IDepartamentoDao departamentoDao;
	
	@Override
	public Departamento save(Departamento departamento) {

		return this.departamentoDao.save(departamento);
	}

	@Override
	public List<Departamento> findAll() {

		return this.departamentoDao.findAll();
	}

	@Override
	public Departamento findByDepartamento(String nombre) {

		return this.departamentoDao.findByDepartamento(nombre);
	}

}
