package com.mateus.sistemapedidos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Pedido {

	private String nomeJogo;
	
	private int idCliente;
}
