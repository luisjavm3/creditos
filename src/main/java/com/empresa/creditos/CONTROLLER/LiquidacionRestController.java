package com.empresa.creditos.CONTROLLER;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.creditos.entity.liquidacion.Liquidacion;
import com.empresa.creditos.service.liquidacion.ILiquidacionService;

@RestController
@RequestMapping("/api")
public class LiquidacionRestController {

	@Autowired
	private ILiquidacionService liquidacionService;
	
	@PostMapping("/liquidaciones")
	public Liquidacion save(@RequestBody Liquidacion liquidacion) {
		
		return this.liquidacionService.save(liquidacion);
	}
	
	@GetMapping("/liquidaciones")
	public List<Liquidacion> findAll(){
		
		return this.liquidacionService.findAll();
	}
	
}
