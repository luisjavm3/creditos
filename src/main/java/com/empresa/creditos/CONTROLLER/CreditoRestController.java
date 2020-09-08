package com.empresa.creditos.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.empresa.creditos.entity.credito.Credito;
import com.empresa.creditos.service.credito.ICreditoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CreditoRestController {

    @Autowired
    private ICreditoService creditoService;

    @GetMapping("/creditos/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {

        Credito credito = creditoService.findById(id);
        Map<String, Object> response = new HashMap<>();

        if (credito == null) {
            response.put("message", "No se encontro ningun credito con dicho ID");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Credito>(credito, HttpStatus.OK);
    }

    @GetMapping("/creditos")
    public List<Credito> findAll() {
        return creditoService.findAll();
    }

}
