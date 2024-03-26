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

import br.com.fiap.restaurante.core.entity.ReservaMesa;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ReservaMesaControllerTest {

        @LocalServerPort
        private int port;

        @Autowired
        private TestRestTemplate restTemplate;

        @SuppressWarnings("null")
        @Test
        void inserirReservaMesa() throws Exception {

                String host = "http://localhost:" + port + "/v1/reserva-mesa";

                ResponseEntity<ReservaMesa> respostaInserir = restTemplate.postForEntity(host, null,
                                ReservaMesa.class);

                ResponseEntity<ReservaMesa[]> respostaConsulta = restTemplate
                                .getForEntity(host,
                                                ReservaMesa[].class);

                assertEquals(HttpStatus.CREATED, respostaInserir.getStatusCode());
                assertEquals(HttpStatus.OK, respostaConsulta.getStatusCode());
                assertEquals(1, respostaConsulta.getBody().length);

        }

}