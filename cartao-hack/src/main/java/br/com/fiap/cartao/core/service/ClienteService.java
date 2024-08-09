package br.com.fiap.cartao.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.cartao.core.entity.Cliente;
import br.com.fiap.cartao.core.repository.ClienteRepository;
import br.com.fiap.cartao.v1.dto.ClienteDTO;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente inserir(ClienteDTO dto) {
        return repository.save(new Cliente(dto));
    }

}