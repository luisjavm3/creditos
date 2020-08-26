package com.empresa.creditos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.creditos.dao.ICobradorDao;
import com.empresa.creditos.entity.Cobrador;

@Service
public class CobradorServiceImpl implements ICobradorService{

	@Autowired
	private ICobradorDao cobradorDao;
	
	@Override
	public Cobrador save(Cobrador cobrador) {
		
		return this.cobradorDao.save(cobrador);
	}
	
	@Override
	public List<Cobrador> findAll(){
		
		return this.cobradorDao.findAll();
	}
	
}
