package br.com.fiap.restaurante.v1.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.restaurante.core.entity.Avaliacao;
import br.com.fiap.restaurante.core.service.AvaliacaoService;
import br.com.fiap.restaurante.v1.dto.AvaliacaoDTO;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.MediaType;

@RestController
@RequestMapping("/v1/avaliacoes")
@OpenAPIDefinition(info = @Info(title = "Api de Restaurantes para o III Tech Challenge"))
@Tag(name = "Avaliações")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @Operation(summary = "Cadastro de Avaliações e Comentários", description = "Após a visita, os usuários podem avaliar o restaurante e deixar comentários sobre sua experiência.")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Inserido com sucesso") })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Avaliacao> inserirAvaliacao(AvaliacaoDTO avaliacaoDTO) {

        return new ResponseEntity<>(avaliacaoService.inserirAvaliacao(avaliacaoDTO), HttpStatus.CREATED);

    }

    @Operation(summary = "Consulta de Avaliações e Comentários", description = "Consulta de todas as avaliações cadastradas")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso") })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Avaliacao>> buscarTodaASAvaliacoes() throws InterruptedException {

        return ResponseEntity.ok(avaliacaoService.buscarTodaASAvaliacoes());

    }

}
