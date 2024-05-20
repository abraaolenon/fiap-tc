package br.com.fiap.pedido.v1.dto;

public record PedidoRespostaDTO(Integer id, String nomeProduto, Integer quantidade, Boolean concluido,
		Boolean pagamentoProcessado) {

}