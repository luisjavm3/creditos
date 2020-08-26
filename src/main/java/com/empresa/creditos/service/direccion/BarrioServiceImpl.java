package com.empresa.creditos.service.direccion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.creditos.dao.IBarrioDao;
import com.empresa.creditos.entity.direccion.Barrio;

@Service
public class BarrioServiceImpl implements IBarrioService{

	@Autowired
	private IBarrioDao barrioDao;
	
	@Override
	public Barrio save(Barrio barrio) {

		return this.barrioDao.save(barrio);
	}

	@Override
	public List<Barrio> findAll() {

		return this.barrioDao.findAll();
	}

}
