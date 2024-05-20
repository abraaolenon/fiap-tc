package br.com.fiap.produto.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.produto.core.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}