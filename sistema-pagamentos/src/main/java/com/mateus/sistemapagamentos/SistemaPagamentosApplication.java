package com.mateus.sistemapagamentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SistemaPagamentosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaPagamentosApplication.class, args);
	}

}
