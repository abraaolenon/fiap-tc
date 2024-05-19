package br.com.fiap.cliente.core.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nomeCompleto;
	private String endereco;
	private String profissao;
	private LocalDateTime dataRegistro;
	private LocalDateTime dataAtualizacao;

	public Cliente(String nomeCompleto, String endereco, String profissao, LocalDateTime dataRegistro) {
		super();
		this.nomeCompleto = nomeCompleto;
		this.endereco = endereco;
		this.profissao = profissao;
		this.dataRegistro = dataRegistro;
	}

}
