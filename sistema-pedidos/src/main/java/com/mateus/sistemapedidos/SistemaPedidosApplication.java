package com.mateus.sistemapedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SistemaPedidosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaPedidosApplication.class, args);
	}

}
