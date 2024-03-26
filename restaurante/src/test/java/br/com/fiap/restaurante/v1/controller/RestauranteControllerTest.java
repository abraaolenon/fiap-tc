package br.com.fiap.restaurante.v1.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.restaurante.v1.dto.RestauranteDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RestauranteControllerTest {

        @LocalServerPort
        private int port;

        @Autowired
        private TestRestTemplate restTemplate;

        @SuppressWarnings("null")
        @Test
        void inserirRestaurante() throws Exception {

                String host = "http://localhost:" + port + "/v1/restaurantes";

                ResponseEntity<RestauranteDTO> respostaInserir = restTemplate.postForEntity(
                                host, null,
                                RestauranteDTO.class);

                ResponseEntity<RestauranteDTO[]> respostaConsulta = restTemplate
                                .getForEntity(host,
                                                RestauranteDTO[].class);

                assertEquals(HttpStatus.CREATED, respostaInserir.getStatusCode());
                assertEquals(HttpStatus.OK, respostaConsulta.getStatusCode());
                assertEquals(1, respostaConsulta.getBody().length);

        }

}