package br.com.fiap.item.core.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.item.core.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

}