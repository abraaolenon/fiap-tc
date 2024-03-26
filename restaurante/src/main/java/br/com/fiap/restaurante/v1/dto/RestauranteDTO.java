package br.com.fiap.restaurante.v1.dto;

public record RestauranteDTO(
                String nome,
                String localizacao,
                String tipoCozinha,
                String horarios,
                Integer capacidade) {
}