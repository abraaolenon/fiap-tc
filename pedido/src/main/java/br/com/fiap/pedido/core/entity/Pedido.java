package br.com.fiap.pedido.core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nomeProduto;
	private Integer quantidade;
	private Boolean concluido;
	private Boolean pagamentoProcessado;

	public Pedido(String nomeProduto, Integer quantidade, Boolean concluido, Boolean pagamentoProcessado) {
		super();
		this.nomeProduto = nomeProduto;
		this.quantidade = quantidade;
		this.concluido = concluido;
		this.pagamentoProcessado = pagamentoProcessado;

	}

}
