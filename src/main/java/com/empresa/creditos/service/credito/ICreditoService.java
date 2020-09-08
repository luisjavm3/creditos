package com.empresa.creditos.service.credito;

import java.util.List;

import com.empresa.creditos.entity.credito.Credito;

public interface ICreditoService {

	Credito save(Credito credito);

	Credito findById(int id);

	void deleteById(int id);

	void delete(Credito credito);

	List<Credito> findAll();

}
