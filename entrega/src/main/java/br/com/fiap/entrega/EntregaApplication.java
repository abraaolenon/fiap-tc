package br.com.fiap.entrega;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EntregaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntregaApplication.class, args);

	}

}
