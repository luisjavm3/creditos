package com.empresa.creditos.service.credito;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.creditos.entity.credito.Credito;

@Service
public class CreditoServiceImpl implements ICreditoService{

	@Autowired
	private ICreditoService creditoService;
	
	@Override
	public Credito save(Credito credito) {
		
		return this.creditoService.save(credito);
	}
	
	@Override
	public List<Credito> findAll(){
		
		return this.creditoService.findAll();
	}
	
}
