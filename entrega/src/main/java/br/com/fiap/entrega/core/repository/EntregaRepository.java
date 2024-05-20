package br.com.fiap.entrega.core.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.entrega.core.entity.Entrega;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Integer> {

}