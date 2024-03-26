package br.com.fiap.restaurante.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.restaurante.core.entity.Avaliacao;
import br.com.fiap.restaurante.core.repository.AvaliacaoRepository;
import br.com.fiap.restaurante.v1.dto.AvaliacaoDTO;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public Avaliacao inserirAvaliacao(AvaliacaoDTO avaliacaoDTO) {
        return avaliacaoRepository.save(new Avaliacao(avaliacaoDTO));

    }

    public List<Avaliacao> buscarTodaASAvaliacoes() {
        return avaliacaoRepository.findAll();

    }

}