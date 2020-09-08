package com.empresa.creditos.service.liquidacion;

import java.util.List;

import com.empresa.creditos.entity.liquidacion.Abono;

public interface IAbonoService {

    Abono save(Abono abono);

    List<Abono> findAll();

}
