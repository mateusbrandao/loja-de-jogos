package com.mateus.sistemapedidos.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

	@GetMapping()
	public ResponseEntity<String> test(){
		return ResponseEntity.ok("Funcionou");
	}
	
}
