package br.com.fiap.produto;

import org.springframework.batch.item.ItemProcessor;

import br.com.fiap.produto.core.entity.Produto;

public class ProdutoProcessor implements ItemProcessor<Produto, Produto> {
    @Override
    public Produto process(@SuppressWarnings("null") Produto item) throws Exception {
        return item;
    }
}
