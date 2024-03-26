package br.com.fiap.restaurante;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class RestauranteApplicationTests {

	@Autowired
	private RestauranteApplication restauranteApplication;

	@Test
	void contextLoads() {
		assertThat(restauranteApplication).isNotNull();

	}

}
