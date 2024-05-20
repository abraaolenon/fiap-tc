package br.com.fiap.pedido;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PedidoApplicationTests {

	@Autowired
	private PedidoApplication pedidoApplication;

	@Test
	void contextLoads() {
		assertThat(pedidoApplication).isNotNull();

	}

}
