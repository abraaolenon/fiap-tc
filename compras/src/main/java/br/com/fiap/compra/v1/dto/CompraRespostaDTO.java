package br.com.fiap.compra.v1.dto;

import java.math.BigDecimal;

public record CompraRespostaDTO(Integer id, String nomeItem, BigDecimal preco, Integer quantidade) {

}