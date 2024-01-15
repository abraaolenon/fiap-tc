package br.com.fiap.parquimetro.v1.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EstacionamentoDTO(
        Integer idParquimetro,
        String placaVeiculo,
        BigDecimal valor,
        LocalDateTime dataHoraInicioEstacionamento,
        LocalDateTime dataHoraFimEstacionamento) {

}