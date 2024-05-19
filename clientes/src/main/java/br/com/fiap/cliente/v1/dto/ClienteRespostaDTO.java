package br.com.fiap.cliente.v1.dto;

import java.time.LocalDateTime;

public record ClienteRespostaDTO(Integer id, String nomeCompleto, String endereco, String profissao,
		LocalDateTime dataRegistro, LocalDateTime dataAtualizacao) {

}