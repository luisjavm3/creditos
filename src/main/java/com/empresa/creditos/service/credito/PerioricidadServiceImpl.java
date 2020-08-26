package com.empresa.creditos.service.credito;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.creditos.dao.IPerioricidadDao;
import com.empresa.creditos.entity.credito.Perioricidad;

@Service
public class PerioricidadServiceImpl implements IPerioricidadService {

	@Autowired
	private IPerioricidadDao perioricidadDao;

	@Override
	public Perioricidad save(Perioricidad perioricidad) {

		return this.perioricidadDao.save(perioricidad);
	}

	@Override
	public Perioricidad findByPerioricidad(int perioricidad) {

		return this.perioricidadDao.findByPerioricidad(perioricidad);
	}

	@Override
	public Perioricidad findByDescripcion(String perioricidad) {

		return this.perioricidadDao.findByDescripcion(perioricidad);
	}

	@Override
	public List<Perioricidad> findAll() {

		return this.perioricidadDao.findAll();
	}

}
