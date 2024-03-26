package br.com.fiap.restaurante.core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import br.com.fiap.restaurante.v1.dto.ReservaMesaDTO;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ReservaMesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nomeRestaurante;
    private LocalDateTime dataHoraReserva;
    private Integer quantidadePessoas;
    private Integer statusMesa;


    public ReservaMesa(ReservaMesaDTO reservaMesaDTO) {
        this.nomeRestaurante = reservaMesaDTO.nomeRestaurante();
        this.dataHoraReserva = reservaMesaDTO.dataHoraReserva();
        this.quantidadePessoas = reservaMesaDTO.quantidadePessoas();
        this.statusMesa = 0;
    }



    
}
