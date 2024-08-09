package br.com.fiap.restaurante;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.fiap.cartao.CartaoApplication;


@SpringBootTest
class CartaoApplicationTests {

	@Autowired
	private CartaoApplication application;

	@Test
	void contextLoads() {
		assertThat(application).isNotNull();

	}

}
