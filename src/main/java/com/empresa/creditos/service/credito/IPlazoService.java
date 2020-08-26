package com.empresa.creditos.service.credito;

import java.util.List;

import com.empresa.creditos.entity.credito.Plazo;

public interface IPlazoService {

	Plazo save(Plazo plazo);

	Plazo findByPlazo(int plazo);

	Plazo findByDescripcion(String descripcion);

	List<Plazo> findAll();

}
