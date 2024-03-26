package br.com.fiap.restaurante.core.entity;

import br.com.fiap.restaurante.v1.dto.AvaliacaoDTO;
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
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nomeRestaurante;
    private Integer notaAvaliacao;
    private String comentario;

    public Avaliacao(AvaliacaoDTO avaliacaoDTO) {
        this.nomeRestaurante = avaliacaoDTO.nomeRestaurante();
        this.notaAvaliacao = avaliacaoDTO.notaAvaliacao();
        this.comentario = avaliacaoDTO.comentario();
    }

}
