package br.com.fiap.produto.core.entity;

import java.math.BigDecimal;

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
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String descricao;
	private BigDecimal preco;
	private Integer quantidade;

	public Produto(String descricao, BigDecimal preco, Integer quantidade) {
		this.descricao = descricao;
		this.preco = preco;
		this.quantidade = quantidade;
	}

}
