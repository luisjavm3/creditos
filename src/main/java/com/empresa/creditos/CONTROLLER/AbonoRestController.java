package com.empresa.creditos.controller;

import java.util.List;

import com.empresa.creditos.entity.liquidacion.Abono;
import com.empresa.creditos.service.liquidacion.IAbonoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AbonoRestController {

    @Autowired
    private IAbonoService abonoService;

    @PostMapping("/abonos")
    public Abono save(@RequestBody Abono abono) {
        return abonoService.save(abono);
    }

    @GetMapping("/abonos")
    public List<Abono> findAll() {
        return abonoService.findAll();
    }

}
