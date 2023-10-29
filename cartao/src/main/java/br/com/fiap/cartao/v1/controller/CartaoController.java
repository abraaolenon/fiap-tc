package br.com.fiap.cartao.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.cartao.core.service.AnaliseCartaoClienteService;
import br.com.fiap.cartao.v1.dto.ParecerDTO;

@RestController
@RequestMapping("/v1/cartoes")
public class CartaoController {

    @Autowired
    private AnaliseCartaoClienteService analiseCartaoClienteService;

    @GetMapping("/limite/cliente/{id}")
    public ResponseEntity<ParecerDTO> parecerValorLimite(@PathVariable Integer id) {

        return ResponseEntity.ok(analiseCartaoClienteService.parecerValorLimite(id));
    }

}
