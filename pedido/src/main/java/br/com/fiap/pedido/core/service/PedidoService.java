package br.com.fiap.pedido.core.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.pedido.core.entity.Pedido;
import br.com.fiap.pedido.core.repository.PedidoRepository;
import br.com.fiap.pedido.v1.dto.PedidoDTO;
import br.com.fiap.pedido.v1.dto.PedidoRespostaDTO;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	public PedidoRespostaDTO inserir(PedidoDTO pedidoDTO) {

		return toPedidoRespostaDTO(
				pedidoRepository.save(new Pedido(pedidoDTO.nomeProduto(), pedidoDTO.quantidade(), false, false)));
	}

	public List<PedidoRespostaDTO> buscarTodos() throws InterruptedException {

		List<PedidoRespostaDTO> retorno = new ArrayList<>();

		pedidoRepository.findAll().forEach(e -> retorno.add(toPedidoRespostaDTO(e)));

		return retorno;
	}

	public PedidoRespostaDTO atualizar(Integer id, PedidoDTO pedidoDTO) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);

		if (!pedido.isPresent()) {
			throw new EntityNotFoundException(MessageFormat.format("Não existe pedido com o id {0}.", id));
		}

		pedido.get().setNomeProduto(pedidoDTO.nomeProduto());
		pedido.get().setQuantidade(pedidoDTO.quantidade());

		return toPedidoRespostaDTO(pedidoRepository.save(pedido.get()));
	}

	public void deletar(Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);

		if (!pedido.isPresent()) {
			throw new EntityNotFoundException(MessageFormat.format("Não existe pedido com o id {0}.", id));
		}

		pedidoRepository.delete(pedido.get());
	}

	public PedidoRespostaDTO conclusao(Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);

		if (!pedido.isPresent()) {
			throw new EntityNotFoundException(MessageFormat.format("Não existe pedido com o id {0}.", id));
		}

		pedido.get().setConcluido(true);

		return toPedidoRespostaDTO(pedidoRepository.save(pedido.get()));
	}

	public PedidoRespostaDTO pagamento(Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);

		if (!pedido.isPresent()) {
			throw new EntityNotFoundException(MessageFormat.format("Não existe pedido com o id {0}.", id));
		}

		if (!pedido.get().getConcluido()) {
			throw new IllegalArgumentException(
					"O processamento de pagamento só é aplicável para pedidos concluídos. Conclua o pedido para realizar o pagamento.");
		}

		pedido.get().setPagamentoProcessado(true);

		return toPedidoRespostaDTO(pedidoRepository.save(pedido.get()));
	}

	private PedidoRespostaDTO toPedidoRespostaDTO(Pedido pedido) {
		return new PedidoRespostaDTO(pedido.getId(), pedido.getNomeProduto(), pedido.getQuantidade(),
				pedido.getConcluido(), pedido.getPagamentoProcessado());

	}

}
