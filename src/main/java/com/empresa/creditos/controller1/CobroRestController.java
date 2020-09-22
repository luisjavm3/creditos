package com.empresa.creditos.controller1;

import java.util.List;

import com.empresa.creditos.entity.Cliente;
import com.empresa.creditos.entity.Cobrador;
import com.empresa.creditos.entity.Cobro;
import com.empresa.creditos.entity.credito.Credito;
import com.empresa.creditos.entity.liquidacion.Liquidacion;
import com.empresa.creditos.service.ICobroService;

import org.springframework.beans.factory.annotation.Autowired;
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

	@GetMapping("/cobros")
	public List<Cobro> findAll() {
		return this.cobroService.findAll();
	}

	@PostMapping("/cobros")
	public Cobro save(@RequestBody Cobro cobro) {
		return this.cobroService.save(cobro);
	}

	@GetMapping("/cobros/{id}")
	public Cobro findById(@PathVariable("id") int id) {
		return this.cobroService.findById(id);
	}

	@GetMapping("/cobros/{id}/creditos")
	public List<Credito> showOnlyItsCredits(@PathVariable("id") int id) {
		return cobroService.findById(id).getCreditos();
	}

	@GetMapping("/cobros/{id}/cobradores")
	public Cobrador getCobrador(@PathVariable("id") int id) {
		return cobroService.findById(id).getCobrador();
	}

	@GetMapping("/cobros/{id}/clientes")
	public List<Cliente> getClientes(@PathVariable("id") int id) {
		return cobroService.findById(id).getClientes();
	}

	@GetMapping("/cobros/{id}/liquidaciones")
	public List<Liquidacion> getLiquidaciones(@PathVariable("id") int id) {
		return cobroService.findById(id).getLiquidaciones();
	}

}
