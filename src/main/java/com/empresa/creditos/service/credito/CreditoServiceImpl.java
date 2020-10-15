package com.empresa.creditos.service.credito;

import com.empresa.creditos.dao.ICreditoDao;
import com.empresa.creditos.entity.credito.Credito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreditoServiceImpl implements ICreditoService {

	@Autowired
	private ICreditoDao creditoDao;

	@Override
	@Transactional
	public Credito save(Credito credito) {
		return creditoDao.save(credito);
	}

	@Override
	@Transactional(readOnly = true)
	public Credito findById(int id) {
		return creditoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Credito> findAll(Pageable pageable) {
		return creditoDao.findAll(pageable);
	}

}
