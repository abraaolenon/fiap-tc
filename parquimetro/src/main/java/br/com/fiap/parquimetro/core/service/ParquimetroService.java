package br.com.fiap.parquimetro.core.service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.cache.annotation.Cacheable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.parquimetro.core.entity.Estacionamento;
import br.com.fiap.parquimetro.core.repository.EstacionamentoRepository;
import br.com.fiap.parquimetro.v1.dto.EstacionamentoDTO;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ParquimetroService {

    @Autowired
    private EstacionamentoRepository estacionamentoRepository;

    private static final Long QUINZE_MINUTOS = 15L;

    public EstacionamentoDTO inserirDadosEstacionamento(Integer idParquimetro, String placaVeiculo) {

        Estacionamento estacionamento = estacionamentoRepository
                .save(new Estacionamento(idParquimetro, placaVeiculo, LocalDateTime.now()));

        return toDTO(estacionamento);
    }

    @Cacheable("estacionamentos")
    public List<EstacionamentoDTO> buscarTodosEstacionamentosPorParquimetro(Integer idParquimetro)
            throws InterruptedException {

        simularAtrasoDaConsulta();

        List<EstacionamentoDTO> retorno = new ArrayList<>();

        estacionamentoRepository.findByIdParquimetro(idParquimetro)
                .forEach(e -> retorno.add(new EstacionamentoDTO(e.getIdParquimetro(), e.getPlacaVeiculo(), e.getValor(),
                        e.getDataHoraInicioEstacionamento(), e.getDataHoraFimEstacionamento())));

        return retorno;
    }

    private void simularAtrasoDaConsulta() throws InterruptedException {

        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public EstacionamentoDTO finalizarEstacionamento(Integer idParquimetro, String placaVeiculo) {

        List<Estacionamento> estacionamentos = validarFinalizacaoEstacionamento(idParquimetro, placaVeiculo);

        Estacionamento estacionamentoAtivo = estacionamentos.get(0);

        estacionamentoAtivo.setDataHoraFimEstacionamento(LocalDateTime.now());
        calcularValorEstacionamento(estacionamentoAtivo);

        return toDTO(estacionamentoRepository.save(estacionamentoAtivo));

    }

    private List<Estacionamento> validarFinalizacaoEstacionamento(Integer idParquimetro, String placaVeiculo) {
        List<Estacionamento> estacionamentos = estacionamentoRepository
                .findByIdParquimetroAndPlacaVeiculo(idParquimetro, placaVeiculo);

        estacionamentos.removeIf(e -> !Objects.isNull(e.getDataHoraFimEstacionamento()));

        if (estacionamentos.isEmpty()) {
            throw new EntityNotFoundException(
                    MessageFormat.format(
                            "Não existe estacionamento com checkout pendente para o veículo {0} no parquímetro {1}.",
                            placaVeiculo, idParquimetro));
        }
        return estacionamentos;
    }

    private static void calcularValorEstacionamento(Estacionamento estacionamentoAtivo) {

        Duration duracaoEstacionamento = Duration.between(estacionamentoAtivo.getDataHoraInicioEstacionamento(),
                LocalDateTime.now());

        if (QUINZE_MINUTOS.compareTo(duracaoEstacionamento.toMinutes()) > 0) {
            estacionamentoAtivo.setValor(BigDecimal.TEN);
        } else {

            BigDecimal valorEstacionamento = BigDecimal.TEN
                    .multiply(BigDecimal.valueOf(duracaoEstacionamento.toMinutes() / QUINZE_MINUTOS));

            estacionamentoAtivo.setValor(valorEstacionamento);
        }
    }

    private EstacionamentoDTO toDTO(Estacionamento estacionamento) {
        return new EstacionamentoDTO(
                estacionamento.getIdParquimetro(),
                estacionamento.getPlacaVeiculo(),
                estacionamento.getValor(),
                estacionamento.getDataHoraInicioEstacionamento(),
                estacionamento.getDataHoraFimEstacionamento());

    }

}
