package com.empresa.creditos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.empresa.creditos.entity.liquidacion.Abono;
import com.empresa.creditos.entity.liquidacion.AbonoId;

public interface IAbonoDao extends JpaRepository<Abono, AbonoId> {

    Abono save(Abono abono);

    List<Abono> findAll();

}
