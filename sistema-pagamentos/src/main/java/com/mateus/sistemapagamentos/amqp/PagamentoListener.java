package com.mateus.sistemapagamentos.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.mateus.sistemapagamentos.model.Pedido;

@Component
public class PagamentoListener {

	@RabbitListener(queues = "receber.pagamento")
	public void recebeMensagem(@Payload Pedido pedido) {
		
		System.out.println("Mensagem recebida: " + pedido);
	}
	
}
