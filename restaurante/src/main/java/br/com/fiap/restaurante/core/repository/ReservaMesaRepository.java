package br.com.fiap.restaurante.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.restaurante.core.entity.ReservaMesa;

@Repository
public interface ReservaMesaRepository extends JpaRepository<ReservaMesa, Integer> {

}