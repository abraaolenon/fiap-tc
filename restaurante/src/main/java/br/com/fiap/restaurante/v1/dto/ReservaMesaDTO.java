package br.com.fiap.restaurante.v1.dto;

import java.time.LocalDateTime;

public record ReservaMesaDTO(
        String nomeRestaurante,
        LocalDateTime dataHoraReserva,
        Integer quantidadePessoas) {
}