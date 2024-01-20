package br.com.fiap.parquimetro.v1.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.parquimetro.v1.dto.EstacionamentoDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ParquimetroControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testeBuscarTodosEstacionamentosPorParquimetro() throws Exception {

        restTemplate.postForEntity(
                "http://localhost:" + port + "/v1/parquimetros/1/estacionamentos/veiculo/placa/abc-123", null,
                EstacionamentoDTO.class);

        ResponseEntity<EstacionamentoDTO[]> resposta = restTemplate
                .getForEntity("http://localhost:" + port + "/v1/parquimetros/1/estacionamentos",
                        EstacionamentoDTO[].class);

        EstacionamentoDTO[] estacionamentos = resposta.getBody();

        assertEquals(1, estacionamentos.length);
        assertEquals(1, estacionamentos[0].idParquimetro());
        assertEquals("abc-123", estacionamentos[0].placaVeiculo());

    }

    @Test
    void testeInserirDadosEstacionamento() throws Exception {

        ResponseEntity<EstacionamentoDTO> resposta = restTemplate.postForEntity(
                "http://localhost:" + port + "/v1/parquimetros/2/estacionamentos/veiculo/placa/xpto-123", null,
                EstacionamentoDTO.class);

        EstacionamentoDTO estacionamento = resposta.getBody();

        assertEquals(2, estacionamento.idParquimetro());
        assertEquals("xpto-123", estacionamento.placaVeiculo());
        assertTrue(estacionamento.dataHoraInicioEstacionamento()
                .isAfter(LocalDateTime.now().minus(1, ChronoUnit.MINUTES)));
        assertNull(estacionamento.dataHoraFimEstacionamento());

    }

    @Test
    void testeFinalizaEstacionamento() throws Exception {

        String recursoParaAtualizacao = String
                .format("http://localhost:%d/v1/parquimetros/3/estacionamentos/veiculo/placa/zyz-123", port);

        restTemplate.postForEntity(recursoParaAtualizacao, null, EstacionamentoDTO.class);

        final ResponseEntity<EstacionamentoDTO> resposta = restTemplate.exchange(
                recursoParaAtualizacao,
                HttpMethod.PUT,
                new HttpEntity<>(null),
                EstacionamentoDTO.class);

        EstacionamentoDTO estacionamento = resposta.getBody();

        assertEquals(3, estacionamento.idParquimetro());
        assertEquals("zyz-123", estacionamento.placaVeiculo());
        assertTrue(estacionamento.dataHoraInicioEstacionamento()
                .isAfter(LocalDateTime.now().minus(1, ChronoUnit.MINUTES)));
        assertTrue(estacionamento.dataHoraFimEstacionamento()
                .isAfter(LocalDateTime.now().minus(1, ChronoUnit.MINUTES)));

    }
}