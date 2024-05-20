package br.com.fiap.produto.v1.dto;

import java.math.BigDecimal;

public record ProdutoDTO(String descricao, BigDecimal preco, Integer quantidade) {}