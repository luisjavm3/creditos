package com.empresa.creditos.service.credito;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.creditos.dao.ICreditoDao;
import com.empresa.creditos.entity.credito.Credito;

@Service
public class CreditoServiceImpl implements ICreditoService {

	@Autowired
	private ICreditoDao creditoDao;

	@Override
	public Credito save(Credito credito) {
		return creditoDao.save(credito);
	}

	@Override
	public Credito findById(int id) {
		return creditoDao.findById(id);
	}

	@Override
	public List<Credito> findAll() {
		return creditoDao.findAll();
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Credito credito) {
		// TODO Auto-generated method stub

	}

}
