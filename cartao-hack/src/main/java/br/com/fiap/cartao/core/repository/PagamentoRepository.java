package br.com.fiap.cartao.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.cartao.core.entity.Pagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

    public List<Pagamento> findByNumero(String numero);
    public List<Pagamento> findByCpf(String cpf);



}