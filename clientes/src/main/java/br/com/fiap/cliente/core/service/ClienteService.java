package br.com.fiap.cliente.core.service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.fiap.cliente.core.entity.Cliente;
import br.com.fiap.cliente.core.repository.ClienteRepository;
import br.com.fiap.cliente.v1.dto.ClienteDTO;
import br.com.fiap.cliente.v1.dto.ClienteRespostaDTO;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public ClienteRespostaDTO inserir(ClienteDTO clienteDTO) {

		Cliente cliente = clienteRepository.save(new Cliente(clienteDTO.nomeCompleto(), clienteDTO.endereco(),
				clienteDTO.profissao(), LocalDateTime.now()));

		return toClienteRespostaDTO(cliente);
	}

	public List<ClienteRespostaDTO> buscarTodos() throws InterruptedException {

		List<ClienteRespostaDTO> retorno = new ArrayList<>();

		clienteRepository.findAll()
				.forEach(e -> retorno.add(toClienteRespostaDTO(e)));

		return retorno;
	}

	public ClienteRespostaDTO atualizar(Integer id, ClienteDTO clienteDTO) {
		Optional<Cliente> cliente = clienteRepository.findById(id);

		if (!cliente.isPresent()) {
			throw new EntityNotFoundException(MessageFormat.format("Não existe cliente com o id {0}.", id));
		}

		cliente.get().setNomeCompleto(clienteDTO.nomeCompleto());
		cliente.get().setEndereco(clienteDTO.endereco());
		cliente.get().setProfissao(clienteDTO.profissao());
		cliente.get().setDataAtualizacao(LocalDateTime.now());

		return toClienteRespostaDTO(clienteRepository.save(cliente.get()));
	}

	public void deletar(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);

		if (!cliente.isPresent()) {
			throw new EntityNotFoundException(MessageFormat.format("Não existe cliente com o id {0}.", id));
		}

		clienteRepository.delete(cliente.get());
	}

	private ClienteRespostaDTO toClienteRespostaDTO(Cliente cliente) {
		return new ClienteRespostaDTO(cliente.getId(), cliente.getNomeCompleto(), cliente.getEndereco(),
				cliente.getProfissao(), cliente.getDataRegistro(), cliente.getDataAtualizacao());

	}

}
