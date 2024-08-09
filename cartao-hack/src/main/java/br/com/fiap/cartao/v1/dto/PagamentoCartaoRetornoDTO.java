package br.com.fiap.cartao.v1.dto;

import java.math.BigDecimal;

public record PagamentoCartaoRetornoDTO(

        BigDecimal valor,
        String descricao,
        String metodo_pagamento,
        String status) {
}
