package br.com.fiap.restaurante.core.entity;

import br.com.fiap.restaurante.v1.dto.RestauranteDTO;
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

public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRestaurante;
    private String nome;
    private String localizacao;
    private String tipoCozinha;
    private String horarios;
    private Integer capacidade;

    public Restaurante(RestauranteDTO restauranteDTO) {
        this.nome = restauranteDTO.nome();
        this.localizacao = restauranteDTO.localizacao();
        this.tipoCozinha = restauranteDTO.tipoCozinha();
        this.horarios = restauranteDTO.horarios();
        this.capacidade = restauranteDTO.capacidade();
    }

}
