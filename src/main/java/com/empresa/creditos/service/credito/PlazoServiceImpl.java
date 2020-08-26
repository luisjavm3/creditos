package com.empresa.creditos.service.credito;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.creditos.dao.IPlazoDao;
import com.empresa.creditos.entity.credito.Plazo;

@Service
public class PlazoServiceImpl implements IPlazoService {

	@Autowired
	private IPlazoDao plazoDao;

	@Override
	public Plazo save(Plazo plazo) {

		return this.plazoDao.save(plazo);
	}

	@Override
	public Plazo findByPlazo(int plazo) {

		return this.plazoDao.findByPlazo(plazo);
	}

	@Override
	public Plazo findByDescripcion(String descripcion) {

		return this.plazoDao.findByDescripcion(descripcion);
	}

	@Override
	public List<Plazo> findAll() {

		return this.plazoDao.findAll();
	}

}
