package br.com.fiap.compra;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.fiap.compra.CompraApplication;

@SpringBootTest
class CompraApplicationTests {

	@Autowired
	private CompraApplication application;

	@Test
	void contextLoads() {
		assertThat(application).isNotNull();

	}

}
