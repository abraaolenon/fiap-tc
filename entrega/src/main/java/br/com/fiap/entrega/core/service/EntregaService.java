package br.com.fiap.entrega.core.service;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.entrega.core.entity.Entrega;
import br.com.fiap.entrega.core.repository.EntregaRepository;
import br.com.fiap.entrega.v1.dto.EntregaDTO;
import br.com.fiap.entrega.v1.dto.EntregaRespostaDTO;
import br.com.fiap.entrega.v1.dto.EntregadorDTO;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EntregaService {

	@Autowired
	private EntregaRepository entregaRepository;

	@PostConstruct
	public void init() {

		entregaRepository.save(
				new Entrega("Alice ", "Rua das Flores 123", "ENVIADO", LocalDate.now().plusDays(10), "Entregador A"));
		entregaRepository.save(new Entrega("Bernardo", "Alameda dos Anjos 537", "ENTREGUE",
				LocalDate.now().minusDays(5), "Entregador B"));
		entregaRepository.save(new Entrega("Fernanda", "Avenida dos Bosques 777", "DEVOLVIDO", null, "Entregador C"));

	}

	public List<EntregaRespostaDTO> buscarTodos() throws InterruptedException {

		List<EntregaRespostaDTO> retorno = new ArrayList<>();

		entregaRepository.findAll()
				.forEach(e -> retorno.add(toEntregaRespostaDTO(e)));

		return retorno;
	}

	public EntregaRespostaDTO buscar(Integer id) throws InterruptedException {

		Optional<Entrega> entrega = entregaRepository.findById(id);

		if (!entrega.isPresent()) {
			throw new EntityNotFoundException(MessageFormat.format("Não existe entrega com o id {0}.", id));
		}

		return toEntregaRespostaDTO(entrega.get());
	}

	public EntregaRespostaDTO inserir(EntregaDTO entregaDTO) {

		if (Objects.isNull(entregaDTO.nomeCliente()) || Objects.isNull(entregaDTO.endereco())) {
			throw new IllegalArgumentException("É preciso enviar o nome e o endereço do cliente para a entrega");
		}

		Entrega entrega = entregaRepository.save(new Entrega(entregaDTO.nomeCliente(), entregaDTO.endereco(), "ENVIADO",
				calcularDataEntrega(entregaDTO.endereco()), null));

		return toEntregaRespostaDTO(entrega);
	}

	public LocalDate calcularDataEntrega(String endereco) {

		return LocalDate.now().plusDays((int) endereco.length());
	}

	public EntregaRespostaDTO atualizarEntregador(Integer id, EntregadorDTO entregadorDTO) {
		Optional<Entrega> entrega = entregaRepository.findById(id);

		if (!entrega.isPresent()) {
			throw new EntityNotFoundException(MessageFormat.format("Não existe entrega com o id {0}.", id));
		}

		entrega.get().setNomeEntregador(entregadorDTO.nomeEntregador());

		return toEntregaRespostaDTO(entregaRepository.save(entrega.get()));
	}

	public void deletar(Integer id) {
		Optional<Entrega> entrega = entregaRepository.findById(id);

		if (!entrega.isPresent()) {
			throw new EntityNotFoundException(MessageFormat.format("Não existe entrega com o id {0}.", id));
		}

		entregaRepository.delete(entrega.get());
	}

	private EntregaRespostaDTO toEntregaRespostaDTO(Entrega entrega) {
		return new EntregaRespostaDTO(entrega.getId(), entrega.getNomeCliente(), entrega.getEndereco(),
				entrega.getStatus(), entrega.getDataEstimadaEntrega(), entrega.getNomeEntregador());

	}

	public List<String> buscarRotaEntrega(String endereco) {

		if (Objects.isNull(endereco)) {
			throw new IllegalArgumentException("É preciso enviar o endereço para buscar a rota.");
		}

		List<String> logradouros = Arrays.asList("Rua Leonidas", "Rua Joacir Silva", "Avenida das Meninas",
				"Travessa Dos Bêbados", "Alameda dos Pássaros", "Rua dos Cataventos", "Avenida da Asa Norte",
				"Beco dos Cegos", "Vila das Virgens", "Rua do Convento");

		Set<Integer> rotas = new HashSet<>();

		int quantidadeLogradourosRota = (int) (Math.random() * 10);

		for (int i = 0; i < quantidadeLogradourosRota; i++) {
			int ordemLogradouros = (int) (Math.random() * 10);
			rotas.add(ordemLogradouros);
		}

		List<String> rotaEscolhida = new ArrayList<>();

		for (Integer numeroRota : rotas) {
			rotaEscolhida.add(logradouros.get(numeroRota));
		}

		rotaEscolhida.add(endereco);

		return rotaEscolhida;
	}

}
