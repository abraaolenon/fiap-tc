package br.com.fiap.item.core.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.item.core.entity.Item;
import br.com.fiap.item.core.repository.ItemRepository;
import br.com.fiap.item.v1.dto.ItemDTO;
import br.com.fiap.item.v1.dto.ItemRespostaDTO;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ItemService {

	@Autowired
	private ItemRepository repository;

	public ItemRespostaDTO inserir(ItemDTO dto) {

		Item item = repository.save(new Item(null, dto.nome(), dto.preco(),
				dto.quantidade()));

		return toRespostaDTO(item);
	}

	public List<ItemRespostaDTO> buscarTodos() {

		List<ItemRespostaDTO> retorno = new ArrayList<>();

		repository.findAll()
				.forEach(e -> retorno.add(toRespostaDTO(e)));

		return retorno;
	}

	public ItemRespostaDTO atualizar(Integer id, ItemDTO dto) {
		Optional<Item> item = repository.findById(id);

		if (!item.isPresent()) {
			throw new EntityNotFoundException(MessageFormat.format("Não existe item com o id {0}.", id));
		}

		item.get().setNome(dto.nome());
		item.get().setPreco(dto.preco());
		item.get().setQuantidade(dto.quantidade());

		return toRespostaDTO(repository.save(item.get()));
	}

	public void deletar(Integer id) {
		Optional<Item> item = repository.findById(id);

		if (!item.isPresent()) {
			throw new EntityNotFoundException(MessageFormat.format("Não existe item com o id {0}.", id));
		}

		repository.delete(item.get());
	}

	private ItemRespostaDTO toRespostaDTO(Item cliente) {
		return new ItemRespostaDTO(cliente.getId(), cliente.getNome(), cliente.getPreco(),
				cliente.getQuantidade());

	}

}
