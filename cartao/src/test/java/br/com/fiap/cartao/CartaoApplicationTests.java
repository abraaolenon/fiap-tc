package br.com.fiap.cartao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CartaoApplicationTests {

	@Autowired
	private CartaoApplication cartaoApplication;

	@Test
	void contextLoads() {
		assertThat(cartaoApplication).isNotNull();

	}

}
