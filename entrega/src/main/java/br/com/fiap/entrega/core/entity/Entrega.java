package br.com.fiap.entrega.core.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Entrega {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nomeCliente;
	private String endereco;
	private String status;
	private LocalDate dataEstimadaEntrega;
	private String nomeEntregador;

	public Entrega(String nomeCliente, String endereco, String status, LocalDate dataEstimadaEntrega, String nomeEntregador) {
		this.nomeCliente = nomeCliente;
		this.endereco = endereco;
		this.status = status;
		this.dataEstimadaEntrega = dataEstimadaEntrega;
		this.nomeEntregador = nomeEntregador;
	}

}
