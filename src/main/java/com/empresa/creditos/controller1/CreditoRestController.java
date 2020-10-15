package com.empresa.creditos.controller1;

import java.util.HashMap;
import java.util.Map;

import com.empresa.creditos.entity.Cobro;
import com.empresa.creditos.entity.credito.Credito;
import com.empresa.creditos.service.ICobroService;
import com.empresa.creditos.service.credito.ICreditoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cobros/{cobroId}")
public class CreditoRestController {

    @Autowired
    private ICreditoService creditoService;

    @Autowired
    private ICobroService cobroService;

    @GetMapping("creditos/page/{page}")
    public ResponseEntity<?> findByPage(@PathVariable("cobroId") int cobroId, @PathVariable("page") Integer page) {

        int pageSize = 3;
        Pageable pageable = PageRequest.of(page, pageSize);

        Map<String, Object> map = new HashMap<>();
        Cobro cobro = null;
        Page<Credito> creditosPage = null;

        try {
            cobro = cobroService.findById(cobroId);

        } catch (Exception e) {
            map.put("error", "Error obteniendo el cobro con id " + cobroId);
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            creditosPage = creditoService.findAll(pageable);

        } catch (Exception e) {
            map.put("error", "Error obteniendo la pagina " + page + " de creditos del cobro ");
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (!creditosPage.hasContent()) {
            map.put("message", "La pagina " + page + " no tiene contenido que mostrar");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }

        return new ResponseEntity<>(creditosPage, HttpStatus.OK);
    }

    // @GetMapping("creditos/ruta/{ruta}")

}
