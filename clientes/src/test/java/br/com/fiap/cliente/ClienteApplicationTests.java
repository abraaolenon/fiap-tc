package br.com.fiap.cliente;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClienteApplicationTests {

	@Autowired
	private ClienteApplication clienteApplication;

	@Test
	void contextLoads() {
		assertThat(clienteApplication).isNotNull();

	}

}
