package br.com.fiap.item.v1.dto;

import java.math.BigDecimal;

public record ItemRespostaDTO(Integer id, String nome, BigDecimal preco, Integer quantidade) {

}