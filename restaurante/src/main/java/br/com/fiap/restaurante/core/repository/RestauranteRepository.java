package br.com.fiap.restaurante.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.restaurante.core.entity.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Integer> {

   List<Restaurante> findByNomeOrLocalizacaoOrTipoCozinha(String nome, String localizacao, String tipoCozinha);

}