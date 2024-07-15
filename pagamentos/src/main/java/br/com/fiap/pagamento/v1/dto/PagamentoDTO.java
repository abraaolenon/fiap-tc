package br.com.fiap.pagamento.v1.dto;

import java.math.BigDecimal;

public record PagamentoDTO(Integer codigoCompra, BigDecimal valor, String meioDePagamento) {

}