package com.empresa.creditos.service;

import java.util.List;

import com.empresa.creditos.dao.ICobroDao;
import com.empresa.creditos.entity.Cobro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CobroServiceImpl implements ICobroService {

	@Autowired
	private ICobroDao cobroDao;

	@Override
	public Cobro save(Cobro cobro) {
		return this.cobroDao.save(cobro);
	}

	@Override
	public List<Cobro> findAll() {
		return cobroDao.findAll();
	}

	@Override
	public Cobro findById(int id) {
		return cobroDao.findById(id);
	}

	@Override
	public Cobro findByIdWithLiquidaciones(int id) {
		return this.cobroDao.findByIdWithLiquidaciones(id);
	}

	@Override
	public Cobro findByIdWithCreditos(int id) {

		return this.cobroDao.findByIdWithCreditos(id);
	}

}
