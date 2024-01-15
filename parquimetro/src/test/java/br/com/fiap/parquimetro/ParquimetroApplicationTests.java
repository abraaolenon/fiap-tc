package br.com.fiap.parquimetro;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ParquimetroApplicationTests {

	@Autowired
	private ParquimetroApplication parquimetroApplication;

	@Test
	void contextLoads() {
		assertThat(parquimetroApplication).isNotNull();

	}

}
