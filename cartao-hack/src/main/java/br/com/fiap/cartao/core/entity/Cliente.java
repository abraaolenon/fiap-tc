package br.com.fiap.cartao.core.entity;

import br.com.fiap.cartao.v1.dto.ClienteDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    String cpf;
    String nome;
    String email;
    String telefone;
    String rua;
    String cidade;
    String estado;
    String cep;
    String pais;

    public Cliente(ClienteDTO dto) {

        this.cpf = dto.cpf();
        this.nome = dto.nome();
        this.email = dto.email();
        this.telefone = dto.telefone();
        this.rua = dto.rua();
        this.cidade = dto.cidade();
        this.estado = dto.estado();
        this.cep = dto.cep();
        this.pais = dto.pais();

    }

}
