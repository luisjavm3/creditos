package com.empresa.creditos.service.direccion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.creditos.dao.IMunicipioDao;
import com.empresa.creditos.entity.direccion.Municipio;

@Service
public class MunicipioserviceImpl implements IMunicipioService{

	@Autowired
	private IMunicipioDao municipioDao;
	
	@Override
	public Municipio save(Municipio municipio) {
		return this.municipioDao.save(municipio);
	}

	@Override
	public List<Municipio> findAll() {
		return this.municipioDao.findAll();
	}

	@Override
	public Municipio findByNombre(String nombre) {
		return municipioDao.findByNombre(nombre);
	}

	@Override
	public void delete(Municipio municipio) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

}
