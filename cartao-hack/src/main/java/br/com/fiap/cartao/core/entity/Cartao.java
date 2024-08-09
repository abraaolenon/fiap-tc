package br.com.fiap.cartao.core.entity;

import java.math.BigDecimal;

import br.com.fiap.cartao.v1.dto.GeracaoCartaoDTO;
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
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    String cpf;
    BigDecimal limite;
    String numero;
    String dataValidade;
    String cvv;

    public Cartao(GeracaoCartaoDTO dto) {

        this.cpf = dto.cpf();
        this.limite = dto.limite();
        this.numero = dto.numero();
        this.dataValidade = dto.data_validade();
        this.cvv = dto.cvv();

    }

}
