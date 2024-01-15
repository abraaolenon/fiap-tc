package br.com.fiap.parquimetro.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.parquimetro.core.entity.Estacionamento;

@Repository
public interface EstacionamentoRepository extends JpaRepository<Estacionamento, Integer> {

    List<Estacionamento> findByIdParquimetro(Integer idParquimetro);

    List<Estacionamento> findByIdParquimetroAndPlacaVeiculo(Integer idParquimetro, String placaVeiculo);

}