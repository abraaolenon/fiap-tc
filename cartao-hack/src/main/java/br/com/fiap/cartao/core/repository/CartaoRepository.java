package br.com.fiap.cartao.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.cartao.core.entity.Cartao;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Integer> {

    public List<Cartao> findByCpf(String cpf);

    public Cartao findByNumero(String numero);


}