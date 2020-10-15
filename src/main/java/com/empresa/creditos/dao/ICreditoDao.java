package com.empresa.creditos.dao;

import com.empresa.creditos.entity.credito.Credito;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICreditoDao extends JpaRepository<Credito, Integer> {

}
