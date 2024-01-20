package br.com.fiap.parquimetro.v1.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.parquimetro.core.service.ParquimetroService;
import br.com.fiap.parquimetro.v1.dto.EstacionamentoDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.MediaType;

@RestController
@RequestMapping("/v1/parquimetros")
@OpenAPIDefinition(info=@Info(title="Api de Parquímetros para o II Tech Challenge"))
@Tag(name = "API para Parquímetros", description = "Gerencia o estacionamento dos veículos registrados no parquímetros")
public class ParquimetrosController {

    @Autowired
    private ParquimetroService estacionamentoService;

    @Operation(description = "Insere os dados de um estacionamento de um veículo registrado em um parquímetro.")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Inserido com sucesso") })
    @PostMapping(path = "/{id}/estacionamentos/veiculo/placa/{placa}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EstacionamentoDTO> inserirDadosEstacionamento(@PathVariable Integer id,
            @PathVariable String placa) {

        return new ResponseEntity<>(estacionamentoService.inserirDadosEstacionamento(id, placa), HttpStatus.CREATED);

    }

    @Operation(description = "Consulta de todos os estacionamentos registrados .")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso") })
    @GetMapping(path = "/{id}/estacionamentos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<EstacionamentoDTO>> buscarTodosEstacionamentosPorParquimetro(
            @PathVariable Integer id) throws InterruptedException {

        return ResponseEntity.ok(estacionamentoService.buscarTodosEstacionamentosPorParquimetro(id));
    }

    @Operation(description = "Realiza o checkout de um veículo estacionado em um parquimetro e realiza o cálculo do valor a ser pago.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Atualizado com com sucesso") })
    @PutMapping(path = "/{id}/estacionamentos/veiculo/placa/{placa}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EstacionamentoDTO> finalizaEstacionamento(@PathVariable Integer id,
            @PathVariable String placa) {

        return ResponseEntity.ok(estacionamentoService.finalizarEstacionamento(id, placa));
    }

}
