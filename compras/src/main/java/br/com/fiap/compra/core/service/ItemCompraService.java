package br.com.fiap.compra.core.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.compra.core.entity.ItemCompra;
import br.com.fiap.compra.core.repository.ItemCompraRepository;
import br.com.fiap.compra.v1.dto.CompraDTO;
import br.com.fiap.compra.v1.dto.CompraRespostaDTO;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ItemCompraService {

	@Autowired
	private ItemCompraRepository repository;

	public CompraRespostaDTO inserir(CompraDTO dto) {

		ItemCompra itemCompra = repository.save(new ItemCompra(null, dto.nomeItem(), dto.preco(),
				dto.quantidade()));

		return toRespostaDTO(itemCompra);
	}

	public List<CompraRespostaDTO> buscarTodos() {

		List<CompraRespostaDTO> retorno = new ArrayList<>();

		repository.findAll()
				.forEach(e -> retorno.add(toRespostaDTO(e)));

		return retorno;
	}

	public CompraRespostaDTO atualizar(Integer id, CompraDTO dto) {
		Optional<ItemCompra> item = repository.findById(id);

		if (!item.isPresent()) {
			throw new EntityNotFoundException(MessageFormat.format("Não existe item com o id {0}.", id));
		}

		item.get().setNomeItem(dto.nomeItem());
		item.get().setPreco(dto.preco());
		item.get().setQuantidade(dto.quantidade());

		return toRespostaDTO(repository.save(item.get()));
	}

	public void deletar(Integer id) {
		Optional<ItemCompra> item = repository.findById(id);

		if (!item.isPresent()) {
			throw new EntityNotFoundException(MessageFormat.format("Não existe item com o id {0}.", id));
		}

		repository.delete(item.get());
	}

	private CompraRespostaDTO toRespostaDTO(ItemCompra cliente) {
		return new CompraRespostaDTO(cliente.getId(), cliente.getNomeItem(), cliente.getPreco(),
				cliente.getQuantidade());

	}

}
