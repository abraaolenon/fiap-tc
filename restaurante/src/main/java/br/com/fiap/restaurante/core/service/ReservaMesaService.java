package br.com.fiap.restaurante.core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.restaurante.core.entity.ReservaMesa;
import br.com.fiap.restaurante.core.repository.ReservaMesaRepository;
import br.com.fiap.restaurante.v1.dto.ReservaMesaDTO;
import br.com.fiap.restaurante.v1.dto.StatusMesaDTO;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ReservaMesaService {

    @Autowired
    private ReservaMesaRepository reservaMesaRepository;

    public ReservaMesa inserirReservaMesa(ReservaMesaDTO reservaMesaDTO) {
        return reservaMesaRepository.save(new ReservaMesa(reservaMesaDTO));

    }

    public List<ReservaMesa> buscarTodaASReservas() {
        return reservaMesaRepository.findAll();

    }

    public ReservaMesa atualizarStatus(StatusMesaDTO statusMesaDTO) {

        Optional<ReservaMesa> reservaOptional = reservaMesaRepository.findById(statusMesaDTO.idReservaMesa());

        if (reservaOptional.isPresent()) {
            ReservaMesa reservaMesa = reservaOptional.get();
            reservaMesa.setStatusMesa(statusMesaDTO.novoStatus());
            return reservaMesaRepository.save(reservaMesa);

        } else {
            throw new EntityNotFoundException(
                    "Não existe a reserva informada na base de dados.");
        }
    }

}