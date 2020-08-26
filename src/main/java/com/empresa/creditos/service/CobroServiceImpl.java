package com.empresa.creditos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.creditos.dao.ICobroDao;
import com.empresa.creditos.entity.Cobro;
import com.empresa.creditos.entity.credito.Credito;

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

		List<Cobro> cobros = this.cobroDao.findAll();

		for (Cobro cobro : cobros) {
			
			try {
				cobro.getTotal();
			} catch (NullPointerException e) {
				cobro.setTotal(0);
			}
			
			List<Credito> creditos = cobro.getCreditos();
			
			for(Credito credito:creditos) {
				try {
					credito.getTotalAbonos();
				} catch (Exception e) {
					credito.setTotalAbonos(0);
				}
			}
		}
	
		return cobros;
	}

	@Override
	public Cobro findById(int id) {

		return this.cobroDao.findById(id);
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
