package br.com.fiap.compra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CompraApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompraApplication.class, args);

	}

}
