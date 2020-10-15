package com.empresa.creditos.controller1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.empresa.creditos.entity.Cliente;
import com.empresa.creditos.entity.Cobro;
import com.empresa.creditos.service.IClienteService;
import com.empresa.creditos.service.ICobroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cobros/{cobroId}")
public class ClienteRestController {

    @Autowired
    private ICobroService cobroService;

    @Autowired
    private IClienteService clienteService;

    @GetMapping("/clientes")
    public ResponseEntity<?> findAll(@PathVariable("cobroId") int cobroId) {

        List<Cliente> clientes = null;
        Map<String, Object> map = new HashMap<>();

        try {
            clientes = cobroService.findById(cobroId).getClientes();

        } catch (Exception e) {

            map.put("error", "Error obteniendo la lista de clientes del cobro " + cobroId);
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (clientes.size() == 0) {
            map.put("message", "el cobro " + cobroId + " no tiene clientes");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }

        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @PutMapping("/clientes")
    @PostMapping("/clientes")
    public ResponseEntity<?> save(@PathVariable("cobroId") int cobroId, @RequestBody @Valid Cliente cliente,
            BindingResult br) {

        Cobro cobro = null;
        Map<String, Object> map = new HashMap<>();

        if (br.hasErrors()) {

            List<String> errors = br.getFieldErrors().stream().map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());

            map.put("message", "Errores validando los datos del cliente");
            map.put("errors", errors);
            return new ResponseEntity<>(map, HttpStatus.NOT_ACCEPTABLE);
        }

        try {

            cobro = cobroService.findById(cobroId);

            if (cobro == null) {
                map.put("error", "No existe ningun cobro con el id " + cobroId);
                return new ResponseEntity<>(map, HttpStatus.NOT_ACCEPTABLE);
            }

        } catch (Exception e) {
            map.put("error", "Error tratando de obtener el cobro con el id " + cobroId);
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {

            cliente.setCobro(cobro);
            cliente = clienteService.save(cliente);

        } catch (Exception e) {
            map.put("error", "Error de servidor tratando de agregar al cliente en el cobro");
            map.put("message", e.getMessage());
            map.put("class exception", e.getClass());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    // @DeleteMapping("/clientes")
    // public ResponseEntity<?> deleteById(@PathVariable("cliente"))

    
}
