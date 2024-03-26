package br.com.fiap.restaurante.v1.dto;

public record AvaliacaoDTO(
        String nomeRestaurante,
        Integer notaAvaliacao,
        String comentario) {
}