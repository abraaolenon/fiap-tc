package br.com.fiap.cartao.core.service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.cartao.core.entity.Cliente;
import br.com.fiap.cartao.core.repository.ClienteRepository;
import br.com.fiap.cartao.v1.dto.ParecerDTO;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AnaliseCartaoClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    private static final BigDecimal DEZ_PORCENTO = BigDecimal.valueOf(0.1);

    private static final BigDecimal TRINTA_PORCENTO = BigDecimal.valueOf(0.3);

    @PostConstruct
    public void init() {

        clienteRepository.save(new Cliente("Alice Caloteira", BigDecimal.ZERO, true, LocalDate.now()));
        clienteRepository.save(new Cliente("Bob Kid", BigDecimal.valueOf(500L), false, LocalDate.now().minusMonths(6)));
        clienteRepository.save(new Cliente("Charlie Personnalité", BigDecimal.valueOf(30000L), false, LocalDate.now().minusYears(10)));

    }

    public ParecerDTO parecerValorLimite(Integer id) {

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Não foi encontrado cliente com o id {0}.", id)));

        if (cliente.getTemCadastroNegativado()) {
            return toDTO(cliente, BigDecimal.ZERO,
                    "O cliente não tem limite de cartão liberado, pois encontra-se com o cadastro negativado");

        }

        LocalDate umAnoAtras = LocalDate.now().minusYears(1);

        if (cliente.getDataCadastro().isAfter(umAnoAtras)) {
            return toDTO(cliente, cliente.getRenda().multiply(DEZ_PORCENTO).setScale(2),
                    "O cliente tem menos de um ano de relacionamento. O valor do cartão foi liberado para o cliente.");

        } else {
            return toDTO(cliente, cliente.getRenda().multiply(TRINTA_PORCENTO).setScale(2),
                    "O cliente tem mais de um ano de relacionamento. O valor do cartão foi liberado para o cliente.");

        }

    }

    private ParecerDTO toDTO(Cliente cliente, BigDecimal limite, String parecer) {
        return new ParecerDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getRenda(),
                cliente.getTemCadastroNegativado() ? "SIM" : "NÃO",
                cliente.getDataCadastro(),
                limite,
                parecer);

    }

}
