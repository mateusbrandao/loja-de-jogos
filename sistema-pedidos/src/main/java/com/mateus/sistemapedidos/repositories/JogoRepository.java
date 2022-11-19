package com.mateus.sistemapedidos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mateus.sistemapedidos.entities.Jogo;

public interface JogoRepository extends JpaRepository<Jogo, Long> {
	
	@Query(value = "select j from Jogo j where j.estoque.totalEstoque"
			+ " > j.estoque.totalReservado")
	List<Jogo> buscarJogosDisponiveis();

}
