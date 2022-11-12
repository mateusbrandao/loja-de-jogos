package com.mateus.sistemapedidos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mateus.sistemapedidos.dto.JogoDto;
import com.mateus.sistemapedidos.entities.Jogo;
import com.mateus.sistemapedidos.services.JogoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

	@Autowired
	JogoService jogoService;
	
	@GetMapping()
	public ResponseEntity<String> test(){
		return ResponseEntity.ok("Funcionou");
	}
	
	@GetMapping(value = "listar-jogos")
	public ResponseEntity<List<JogoDto>> listarJogos(){
		
		List<JogoDto> listaJogos= jogoService.buscarTodosOsjogos();
		
		return ResponseEntity.ok(listaJogos);
	}
	
	@GetMapping(value = "listar-jogos-disponiveis")
	public ResponseEntity<List<JogoDto>> listarJogosDisponiveis(){
		
		List<JogoDto> listaJogos= jogoService.buscarJogosDisponiveis();
		
		return ResponseEntity.ok(listaJogos);
	}
	
	
}
