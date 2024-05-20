package br.com.fiap.produto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProdutoApplicationTests {

	@Autowired
	private ProdutoApplication produtoApplication;

	@Test
	void contextLoads() {
		assertThat(produtoApplication).isNotNull();

	}

}
