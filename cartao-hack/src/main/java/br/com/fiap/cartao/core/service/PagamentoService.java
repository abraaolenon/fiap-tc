package br.com.fiap.cartao.core.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.cartao.core.entity.Cartao;
import br.com.fiap.cartao.core.entity.Pagamento;
import br.com.fiap.cartao.core.repository.CartaoRepository;
import br.com.fiap.cartao.core.repository.ClienteRepository;
import br.com.fiap.cartao.core.repository.PagamentoRepository;
import br.com.fiap.cartao.v1.dto.PagamentoCartaoDTO;
import br.com.fiap.cartao.v1.dto.PagamentoCartaoRetornoDTO;
import jakarta.validation.ValidationException;

@Service
public class PagamentoService {

    private static final String MSG_LIMITE_INDISPONIVEL = "O pagamento não pode ser feito, pois o cliente não possui limite disponível nesse cartão para o valor desse pagamento.";

    private static final String MSG_CARTAO_NAO_CORRESPONDE_CLIENTE = "O pagamento não pode ser feito, pois o cartão não pertence ao CPF informado.";

    private static final String MSG_CODIGO_VERIFICAO_DIFERENTE = "O pagamento não pode ser feito, pois o código de verificação não pertence ao cartão informado.";

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public Pagamento efetuarPagamento(PagamentoCartaoDTO dto) {

        List<Pagamento> pagamentos = pagamentoRepository.findByNumero(dto.numero());

        BigDecimal valorTotalPagamentos = pagamentos.stream()
                .map(Pagamento::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Cartao cartao = cartaoRepository.findByNumero(dto.numero());

        BigDecimal limiteDisponivel = cartao.getLimite().subtract(valorTotalPagamentos);
        BigDecimal limiteRemanescente = limiteDisponivel.subtract(dto.valor());

        if (limiteRemanescente.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationException(MSG_LIMITE_INDISPONIVEL);
        }

        if (!cartao.getCpf().equals(dto.cpf())) {
            throw new ValidationException(MSG_CARTAO_NAO_CORRESPONDE_CLIENTE);
        }

        if (!cartao.getCvv().equals(dto.cvv())) {
            throw new ValidationException(MSG_CODIGO_VERIFICAO_DIFERENTE);
        }

        return pagamentoRepository.save(new Pagamento(dto));

    }

    public List<PagamentoCartaoRetornoDTO> buscarPagamentosPorCliente(Integer idCliente) {

        String cpf = clienteRepository.findById(idCliente).get().getCpf();

        List<Pagamento> pagamentos = pagamentoRepository.findByCpf(cpf);

        List<PagamentoCartaoRetornoDTO> retorno = new ArrayList<>();

        pagamentos.forEach(p -> retorno
                .add(new PagamentoCartaoRetornoDTO(p.getValor(), p.getDescricao(), "cartao_credito", p.getStatus())));

        return retorno;
    }

}