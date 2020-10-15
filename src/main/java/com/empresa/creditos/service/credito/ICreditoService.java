package com.empresa.creditos.service.credito;

import com.empresa.creditos.entity.credito.Credito;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICreditoService {

	Credito save(Credito credito);

	Credito findById(int id);

	Page<Credito> findAll(Pageable pageable);

}
