package br.com.fiap.cartao.v1.dto;

public record ClienteDTO(
                String cpf,
                String nome,
                String email,
                String telefone,
                String rua,
                String cidade,
                String estado,
                String cep,
                String pais

) {
}
