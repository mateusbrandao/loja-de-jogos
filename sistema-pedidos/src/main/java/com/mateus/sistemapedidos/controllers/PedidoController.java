package com.mateus.sistemapedidos.controllers;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mateus.sistemapedidos.dto.JogoDto;
import com.mateus.sistemapedidos.services.JogoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

	@Autowired
	JogoService jogoService;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@GetMapping()
	public ResponseEntity<String> test() {
		return ResponseEntity.ok("Funcionou");
	}

	@GetMapping(value = "listar-jogos")
	public ResponseEntity<List<JogoDto>> listarJogos() {

		List<JogoDto> listaJogos = jogoService.buscarTodosOsjogos();

		return ResponseEntity.ok(listaJogos);
	}

	@GetMapping(value = "listar-jogos-disponiveis")
	public ResponseEntity<List<JogoDto>> listarJogosDisponiveis() {

		List<JogoDto> listaJogos = jogoService.buscarJogosDisponiveis();

		return ResponseEntity.ok(listaJogos);
	}

	@PostMapping(value = "comprar-jogo")
	public ResponseEntity<String> comprarJogo(@RequestParam(value = "nomeJogo") String nomeJogo) {

		String respostaPedido = "";

		try {
			respostaPedido = jogoService.comprarJogo(nomeJogo);
		} catch (ResponseStatusException e) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(e.getMessage());
		}
		
		
		rabbitTemplate.convertAndSend("pedidos","pagamento.novo", nomeJogo);

		return ResponseEntity.ok(respostaPedido);
	}

}
