package br.com.fiap.compra.v1.dto;

import java.math.BigDecimal;

public record CompraDTO(String nomeItem, BigDecimal preco, Integer quantidade) {

}