package com.mateus.sistemapedidos.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mateus.sistemapedidos.dto.JogoDto;
import com.mateus.sistemapedidos.repositories.JogoRepository;

@Service
public class JogoService {

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
	
}
