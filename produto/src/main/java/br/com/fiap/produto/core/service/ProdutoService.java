package br.com.fiap.produto.core.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.produto.core.entity.Produto;
import br.com.fiap.produto.core.repository.ProdutoRepository;
import br.com.fiap.produto.v1.dto.ProdutoDTO;
import br.com.fiap.produto.v1.dto.ProdutoRespostaDTO;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public List<ProdutoRespostaDTO> buscarTodos() throws InterruptedException {

		List<ProdutoRespostaDTO> retorno = new ArrayList<>();

		produtoRepository.findAll().forEach(e -> retorno.add(toProdutoRespostaDTO(e)));

		return retorno;
	}

	public ProdutoRespostaDTO inserir(ProdutoDTO produtoDTO) {

		Produto produto = produtoRepository
				.save(new Produto(produtoDTO.descricao(), produtoDTO.preco(), produtoDTO.quantidade()));

		return toProdutoRespostaDTO(produto);
	}

	public ProdutoRespostaDTO buscar(Integer id) throws InterruptedException {

		Optional<Produto> entrega = produtoRepository.findById(id);

		if (!entrega.isPresent()) {
			throw new EntityNotFoundException(MessageFormat.format("Não existe um produto com o id {0}.", id));
		}

		return toProdutoRespostaDTO(entrega.get());
	}

	public void deletar(Integer id) {
		Optional<Produto> produto = produtoRepository.findById(id);

		if (!produto.isPresent()) {
			throw new EntityNotFoundException(MessageFormat.format("Não existe um produto com o id {0}.", id));
		}

		produtoRepository.delete(produto.get());
	}

	private ProdutoRespostaDTO toProdutoRespostaDTO(Produto produto) {
		return new ProdutoRespostaDTO(produto.getId(), produto.getDescricao(), produto.getPreco(),
				produto.getQuantidade());

	}

}
