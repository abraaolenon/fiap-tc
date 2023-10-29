package br.com.fiap.cartao.v1.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.cartao.v1.dto.ParecerDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CartaoControllerTests {
    
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testeClienteInexistenteNoCadastro() throws Exception {

        String resposta = this.restTemplate
                .getForObject("http://localhost:" + port + "/v1/cartoes/limite/cliente/123", String.class);

        assertTrue(resposta.contains("123"));

        ResponseEntity<String> respostaStatusCode = this.restTemplate
                .getForEntity("http://localhost:" + port + "/v1/cartoes/limite/cliente/123", String.class);
        assertEquals(HttpStatus.NOT_FOUND, respostaStatusCode.getStatusCode());

    }

    @Test
    void testeClienteCadatroNegativado() throws Exception {

        ParecerDTO resposta = this.restTemplate
                .getForObject("http://localhost:" + port + "/v1/cartoes/limite/cliente/1", ParecerDTO.class);

        assertEquals(1, resposta.id());
        assertEquals(0, BigDecimal.ZERO.compareTo(resposta.limite()));
        assertEquals("SIM", resposta.temCadastroNegativado());
    }

    @Test
    void testeClienteComMenosDeUmAnoDeCadastro() throws Exception {

        ParecerDTO resposta = this.restTemplate
                .getForObject("http://localhost:" + port + "/v1/cartoes/limite/cliente/2", ParecerDTO.class);

        assertEquals(2, resposta.id());
        assertEquals(0, BigDecimal.valueOf(50).compareTo(resposta.limite()));
        assertTrue(resposta.dataCadastro().isAfter(LocalDate.now().minusYears(1)));
    }

    @Test
    void testeClienteComMaisDeUmAnoDeCadastro() throws Exception {

        ParecerDTO resposta = this.restTemplate
                .getForObject("http://localhost:" + port + "/v1/cartoes/limite/cliente/3", ParecerDTO.class);

        assertEquals(3, resposta.id());
        assertEquals(0, BigDecimal.valueOf(9000).compareTo(resposta.limite()));
        assertTrue(resposta.dataCadastro().isBefore(LocalDate.now().minusYears(1)));
    }

}
