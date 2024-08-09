package br.com.fiap.cartao.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.cartao.core.entity.Cartao;
import br.com.fiap.cartao.core.repository.CartaoRepository;
import br.com.fiap.cartao.v1.dto.GeracaoCartaoDTO;

@Service
public class CartaoService {

    private static final int NUMERO_LIMITE_CARTOES = 2;
    private static final String MSG_NUMERO_LIMITE_CARTOES = "A geração não pode ser concluída, pois o cliente pode ter no máximo dois cartões.";

    @Autowired
    private CartaoRepository repository;

    public Cartao gerar(GeracaoCartaoDTO dto) {

        List<Cartao> cartoes = repository.findByCpf(dto.cpf());

        if (cartoes.size() < NUMERO_LIMITE_CARTOES) {
            return repository.save(new Cartao(dto));

        } else {
            throw new IllegalArgumentException(MSG_NUMERO_LIMITE_CARTOES);
        }

    }

}