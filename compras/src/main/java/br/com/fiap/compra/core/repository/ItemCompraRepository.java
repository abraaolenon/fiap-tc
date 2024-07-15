package br.com.fiap.compra.core.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.compra.core.entity.ItemCompra;

@Repository
public interface ItemCompraRepository extends JpaRepository<ItemCompra, Integer> {

}