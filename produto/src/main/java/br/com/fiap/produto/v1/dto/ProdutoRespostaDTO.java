package br.com.fiap.produto.v1.dto;

import java.math.BigDecimal;

public record ProdutoRespostaDTO(Integer id, String descricao, BigDecimal preco, Integer quantidade) {}