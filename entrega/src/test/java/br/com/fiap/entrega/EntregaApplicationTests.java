package br.com.fiap.entrega;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EntregaApplicationTests {

	@Autowired
	private EntregaApplication entregaApplication;

	@Test
	void contextLoads() {
		assertThat(entregaApplication).isNotNull();

	}

}
