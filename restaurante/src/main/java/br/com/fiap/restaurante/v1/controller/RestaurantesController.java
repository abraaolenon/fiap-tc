package br.com.fiap.restaurante.v1.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.restaurante.core.service.RestauranteService;
import br.com.fiap.restaurante.v1.dto.ChaveBuscaRestauranteDTO;
import br.com.fiap.restaurante.v1.dto.RestauranteDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.MediaType;

@RestController
@RequestMapping("/v1/restaurantes")
@OpenAPIDefinition(info = @Info(title = "Api de Restaurantes para o III Tech Challenge"))
@Tag(name = "Restaurantes")
public class RestaurantesController {

    @Autowired
    private RestauranteService restauranteService;

    @Operation(summary = "Cadastro de Restaurantes", description = "Os restaurantes podem se cadastrar no sistema, fornecendo informações como nome, localização, tipo de cozinha, horários de funcionamento e capacidade.")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Inserido com sucesso") })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestauranteDTO> inserirRestaurante(RestauranteDTO restauranteDTO) {

        return new ResponseEntity<>(restauranteService.inserirRestaurante(restauranteDTO), HttpStatus.CREATED);

    }

    @Operation(summary = "Busca de Restaurantes", description = "Os usuários podem buscar restaurantes por nome, localização ou tipo de cozinha.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso") })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<RestauranteDTO>> buscarRestaurante(
            ChaveBuscaRestauranteDTO chaveBuscaRestauranteDTO) throws InterruptedException {

        return ResponseEntity.ok(restauranteService.buscarRestaurante(chaveBuscaRestauranteDTO));
    }

}
