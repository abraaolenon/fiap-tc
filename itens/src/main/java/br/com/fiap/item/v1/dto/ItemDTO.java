package br.com.fiap.item.v1.dto;

import java.math.BigDecimal;

public record ItemDTO(String nome, BigDecimal preco, Integer quantidade) {

}