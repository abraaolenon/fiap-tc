package br.com.fiap.restaurante.v1.dto;

public record ChaveBuscaRestauranteDTO(
        String nome,
        String localizacao,
        String tipoCozinha) {
}