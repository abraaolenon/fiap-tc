package br.com.fiap.cartao.core.entity;

import java.math.BigDecimal;

import br.com.fiap.cartao.v1.dto.PagamentoCartaoDTO;
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
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    String cpf;
    String numero;
    BigDecimal valor;
    String descricao;
    String status;

    public Pagamento(PagamentoCartaoDTO dto) {

        this.cpf = dto.cpf();
        this.numero = dto.numero();
        this.valor = dto.valor();
        this.descricao = dto.descricao();
        this.status = "APROVADO";

    }

}
