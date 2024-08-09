package br.com.fiap.cartao.v1.dto;

import java.math.BigDecimal;

public record PagamentoCartaoDTO(
                String cpf,
                String numero,
                String data_validade,
                String cvv,
                BigDecimal valor,
                String descricao) {
}