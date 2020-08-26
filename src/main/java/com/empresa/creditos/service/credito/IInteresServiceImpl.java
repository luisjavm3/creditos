package com.empresa.creditos.service.credito;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.creditos.dao.IInteresDao;
import com.empresa.creditos.entity.credito.Interes;

@Service
public class IInteresServiceImpl implements IInteresService{

	@Autowired
	private IInteresDao interesDao;
	
	@Override
	public Interes save(Interes interes) {

		return this.interesDao.save(interes);
	}

	@Override
	public Interes findByInteres(int interes) {

		return this.interesDao.findByInteres(interes);
	}

	@Override
	public List<Interes> findAll() {

		return this.interesDao.findAll();
	}

}
