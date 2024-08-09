package br.com.fiap.cartao.v1.dto;

import java.math.BigDecimal;

public record GeracaoCartaoDTO(
                String cpf,
                BigDecimal limite,
                String numero,
                String data_validade,
                String cvv) {
}