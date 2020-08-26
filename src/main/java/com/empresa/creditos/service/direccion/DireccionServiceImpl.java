package com.empresa.creditos.service.direccion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.creditos.dao.IDireccionDao;
import com.empresa.creditos.entity.direccion.Direccion;

@Service
public class DireccionServiceImpl implements IDireccionService{

	@Autowired
	private IDireccionDao direccionDao;
	
	@Override
	public Direccion save(Direccion direccion) {

		return this.direccionDao.save(direccion);
	}

	@Override
	public List<Direccion> findAll() {

		return this.direccionDao.findAll();
	}

}
