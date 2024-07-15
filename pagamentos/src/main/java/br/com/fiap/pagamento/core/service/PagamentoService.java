package br.com.fiap.pagamento.core.service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.pagamento.core.entity.Pagamento;
import br.com.fiap.pagamento.core.repository.PagamentoRepository;
import br.com.fiap.pagamento.v1.dto.PagamentoDTO;
import br.com.fiap.pagamento.v1.dto.PagamentoRespostaDTO;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository repository;

	public PagamentoRespostaDTO inserir(PagamentoDTO dto) {

		Pagamento itemCompra = repository.save(new Pagamento(null, dto.codigoCompra(), dto.valor(),
				dto.meioDePagamento(), null));

		return toRespostaDTO(itemCompra);
	}

	

	public PagamentoRespostaDTO atualizar(Integer id, PagamentoDTO dto) {
		Optional<Pagamento> item = repository.findById(id);

		if (!item.isPresent()) {
			throw new EntityNotFoundException(MessageFormat.format("Não existe pagamento com o id {0}.", id));
		}

		item.get().setCodigoCompra(dto.codigoCompra());
		item.get().setValor(dto.valor());
		item.get().setMeioDePagamento(dto.meioDePagamento());
		item.get().setDataHoraConfirmacao(LocalDateTime.now());

		return toRespostaDTO(repository.save(item.get()));
	}


	private PagamentoRespostaDTO toRespostaDTO(Pagamento pagamento) {
		return new PagamentoRespostaDTO(pagamento.getId(), pagamento.getCodigoCompra(), pagamento.getValor(),
				pagamento.getMeioDePagamento(), pagamento.getDataHoraConfirmacao());

	}

}
