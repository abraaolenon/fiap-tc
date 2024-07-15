package br.com.fiap.pagamento.v1.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.pagamento.core.service.PagamentoService;
import br.com.fiap.pagamento.v1.dto.PagamentoDTO;
import br.com.fiap.pagamento.v1.dto.PagamentoRespostaDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/pagamentos")
@OpenAPIDefinition(info = @Info(title = "Api de pagamentos para o V Tech Challenge"))
@Tag(name = "Microsserviço de Gerenciamento de pagamentos", description = "Este serviço será responsável por simular as operações de pagamentos.")
public class PagamentosController {

    @Autowired
    private PagamentoService service;

    @Operation(description = "Insere o pagamento de uma compra.")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Inserido com sucesso") })
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagamentoRespostaDTO> inserir(PagamentoDTO dto) {

        return new ResponseEntity<>(service.inserir(dto), HttpStatus.CREATED);

    }

    
    @Operation(description = "Confirmar o pagamento de uma compra.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Confirmado com com sucesso") })
    @PutMapping(path = "/confirmar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagamentoRespostaDTO> atualizar(@PathVariable Integer id, PagamentoDTO dto) {

        return ResponseEntity.ok(service.atualizar(id, dto));
    }

  

}
