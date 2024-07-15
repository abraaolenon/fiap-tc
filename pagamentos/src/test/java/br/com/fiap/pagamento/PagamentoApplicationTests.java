package br.com.fiap.pagamento;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.fiap.pagamento.PagamentoApplication;

@SpringBootTest
class PagamentoApplicationTests {

	@Autowired
	private PagamentoApplication application;

	@Test
	void contextLoads() {
		assertThat(application).isNotNull();

	}

}
