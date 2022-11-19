package com.mateus.sistemapedidos.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mateus.sistemapedidos.dto.JogoDto;
import com.mateus.sistemapedidos.entities.Jogo;
import com.mateus.sistemapedidos.repositories.JogoRepository;

@Service
public class JogoService {

	String MENSAGEM_ESSE_JOGO_NAO_FOI_ENCONTRADO = "Nao ha nenhum jogo com esse nome";
	String MENSAGEM_JOGO_INDISPONIVEL = "No momento nao ha unidades desse jogo disponiveis no estoque";
	String MENSAGEM_JOGO_ENCAMINHADO_PARA_PAGAMENTO = "Obrigado pela solicitacao. O pedido foi encaminhado para a area de pagamento";
	
	
	@Autowired
	private JogoRepository jogoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<JogoDto> buscarTodosOsjogos() {
		
		return jogoRepository.findAll().stream()
				.map(j -> modelMapper.map(j, JogoDto.class))
				.collect(Collectors.toList());
		
	}

	
	public List<JogoDto> buscarJogosDisponiveis() {
		
		return jogoRepository.buscarJogosDisponiveis().stream()
				.map(j -> modelMapper.map(j, JogoDto.class))
				.collect(Collectors.toList());
	}


	   public String comprarJogo(String nomeJogo) {
	
		   Jogo jogo = jogoRepository.findByNome(nomeJogo);
		   
		   if(jogo == null) {
			   throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
					   MENSAGEM_ESSE_JOGO_NAO_FOI_ENCONTRADO);
		   }
		   
		   if(jogo.getEstoque().getTotalEstoque() <= jogo.getEstoque().getTotalReservado()) {
			   throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
					   MENSAGEM_JOGO_INDISPONIVEL);
		   }
		   
		   incrementarQuantidadeReservada(jogo);
		   
		   return MENSAGEM_JOGO_ENCAMINHADO_PARA_PAGAMENTO;
		   
	}


	private void incrementarQuantidadeReservada(Jogo jogo) {
		jogo.getEstoque().setTotalReservado(jogo.getEstoque().getTotalReservado()+1);
		jogoRepository.save(jogo);
	}
	
}
