package br.com.fiap.entrega.v1.dto;

import java.time.LocalDate;

public record EntregaRespostaDTO(Integer id, String nomeCliente, String endereco, String status,
		LocalDate dataEstimadaEntrega, String nomeEntregador) {

}