package com.empresa.creditos.service.liquidacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.creditos.dao.ILiquidacionDao;
import com.empresa.creditos.entity.liquidacion.Liquidacion;

@Service
public class LiquidacionServiceImpl implements ILiquidacionService{

	@Autowired
	private ILiquidacionDao liquidacionDao;
	
	@Override
	public Liquidacion save(Liquidacion liquidacion) {

		return this.liquidacionDao.save(liquidacion);
	}

	@Override
	public List<Liquidacion> findAll() {

		return this.liquidacionDao.findAll();
	}

}
