package com.empresa.creditos.service.liquidacion;

import java.util.List;

import com.empresa.creditos.dao.IAbonoDao;
import com.empresa.creditos.entity.liquidacion.Abono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AbonoServiceImpl implements IAbonoService {

    @Autowired
    private IAbonoDao abonoDao;

    @Override
    public Abono save(Abono abono) {
        return abonoDao.save(abono);
    }

    @Override
    public List<Abono> findAll() {
        return abonoDao.findAll();
    }

}
