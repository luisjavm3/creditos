package com.empresa.creditos.CONTROLLER;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.creditos.entity.Cobro;
import com.empresa.creditos.service.ICobroService;

@RestController
@RequestMapping("/api")
public class CobroRestController {

	@Autowired
	private ICobroService cobroService;

	@PostMapping("/cobros")
	public Cobro save(@RequestBody Cobro cobro) {
		return this.cobroService.save(cobro);
	}

	@GetMapping("/cobros")
	public List<Cobro> findAll() {
		return this.cobroService.findAll();
	}

	@GetMapping("/cobros/{id}")
	public Cobro findById(@PathVariable("id") int id){
		return this.cobroService.findById(id);
	}

}
