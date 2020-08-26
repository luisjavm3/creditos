package com.empresa.creditos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.creditos.entity.liquidacion.Abono;

public interface IAbonoDao extends JpaRepository<Abono, Integer>{

}
