package br.com.fiap.cliente;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.fiap.item.ItemApplication;

@SpringBootTest
class ItemApplicationTests {

	@Autowired
	private ItemApplication application;

	@Test
	void contextLoads() {
		assertThat(application).isNotNull();

	}

}
