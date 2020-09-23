package com.empresa.creditos.controller1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.empresa.creditos.entity.Cliente;
import com.empresa.creditos.entity.Cobrador;
import com.empresa.creditos.entity.Cobro;
import com.empresa.creditos.entity.credito.Credito;
import com.empresa.creditos.entity.liquidacion.Liquidacion;
import com.empresa.creditos.service.ICobroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CobroRestController {

	@Autowired
	private ICobroService cobroService;

	@GetMapping("/cobros/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") int id) {

		Cobro cobro = null;
		Map<String, Object> map = new HashMap<>();

		try {
			cobro = this.cobroService.findById(id);

		} catch (DataAccessException e) {

			map.put("error", "Errores en el acceso a datos");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (cobro == null) {
			map.put("message", "No se encontró ningun cobro con ese ID");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Cobro>(cobro, HttpStatus.OK);
		}
	}

	@GetMapping("/cobros")
	public ResponseEntity<?> findAll() {

		Map<String, Object> map = new HashMap<>();
		List<Cobro> cobros = null;

		try {
			cobros = this.cobroService.findAll();

		} catch (Exception e) {
			map.put("error", "Error obteniendo el listado de cobros");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (cobros.size() == 0) {
			map.put("message", "No hay cobros creados actualmente");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}

		return new ResponseEntity<List<Cobro>>(cobros, HttpStatus.OK);
	}

	@PostMapping("/cobros")
	public ResponseEntity<?> save(@Valid @RequestBody Cobro cobro) {

		Map<String, Object> map = new HashMap<>();

		try {
			cobro = this.cobroService.save(cobro);

		} catch (Exception e) {
			map.put("error", "Hubo un error tratando de guardar el cobro en la base de datos!");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Cobro>(cobro, HttpStatus.CREATED);
	}

	@GetMapping("/cobros/{id}/creditos")
	public ResponseEntity<?> getCreditos(@PathVariable("id") int id) {

		List<Credito> creditos = null;
		Map<String, Object> map = new HashMap<>();

		try {
			creditos = cobroService.findById(id).getCreditos();

		} catch (Exception e) {
			map.put("error", "Error obteniendo la lista de creditos del cobro con ID: " + id
					+ " Puede que dicho cobro no exista");
			return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (creditos.size() == 0) {
			map.put("message", "El cobro no tiene creditos activos");
			return new ResponseEntity<>(map, HttpStatus.OK);
		}

		return new ResponseEntity<>(creditos, HttpStatus.OK);
	}

	@GetMapping("/cobros/{id}/cobradores")
	public ResponseEntity<?> getCobrador(@PathVariable("id") int id) {

		Cobrador cobrador = null;
		Map<String, Object> map = new HashMap<>();

		try {
			cobrador = cobroService.findById(id).getCobrador();

		} catch (Exception e) {

			map.put("error", "Error obteniendo el cobrador del cobro " + id);
			return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(cobrador, HttpStatus.OK);
	}

	@GetMapping("/cobros/{id}/clientes")
	public ResponseEntity<?> getClientes(@PathVariable("id") int id) {

		List<Cliente> clientes = null;
		Map<String, Object> map = new HashMap<>();

		try {
			clientes = cobroService.findById(id).getClientes();

		} catch (Exception e) {

			map.put("error", "Error obteniendo la lista de clientes del cobro " + id);
			return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (clientes.size() == 0) {
			map.put("message", "el cobro " + id + " no tiene clientes");
			return new ResponseEntity<>(map, HttpStatus.OK);
		}

		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}

	@GetMapping("/cobros/{id}/liquidaciones")
	public ResponseEntity<?> getLiquidaciones(@PathVariable("id") int id) {

		List<Liquidacion> liquidaciones = null;
		Map<String, Object> map = new HashMap<>();

		try {
			liquidaciones = cobroService.findById(id).getLiquidaciones();

		} catch (Exception e) {

			map.put("error", "Error obteniendo las liquidaciones del cobro " + id);
			return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (liquidaciones.size() == 0) {
			map.put("message", "El cobro no tiene liquidaciones realizadas aun");
			return new ResponseEntity<>(map, HttpStatus.OK);
		}

		return new ResponseEntity<>(liquidaciones, HttpStatus.OK);
	}

}
