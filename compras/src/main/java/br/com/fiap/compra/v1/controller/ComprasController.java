package br.com.fiap.compra.v1.controller;

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

import br.com.fiap.compra.core.service.ItemCompraService;
import br.com.fiap.compra.v1.dto.CompraDTO;
import br.com.fiap.compra.v1.dto.CompraRespostaDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/compras")
@OpenAPIDefinition(info = @Info(title = "Api de compras para o V Tech Challenge"))
@Tag(name = "Microsserviço de Gerenciamento de compras", description = "Este serviço será responsável por todas as operações relacionadas aos itens de compras, incluindo a criação, leitura, atualização e exclusão de registros (CRUD).")
public class ComprasController {

    @Autowired
    private ItemCompraService service;

    @Operation(description = "Insere um item na compra.")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Inserido com sucesso") })
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompraRespostaDTO> inserir(CompraDTO dto) {

        return new ResponseEntity<>(service.inserir(dto), HttpStatus.CREATED);

    }

    @Operation(description = "Consulta de todas os itens de compras.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso") })
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<CompraRespostaDTO>> buscarTodos() {

        return ResponseEntity.ok(service.buscarTodos());
    }

    @Operation(description = "Atualiza um item de compra.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Atualizado com com sucesso") })
    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompraRespostaDTO> atualizar(@PathVariable Integer id, CompraDTO dto) {

        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @Operation(description = "Deleta um item de compra.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Deletado com sucesso") })
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deletar(@PathVariable Integer id) {

        service.deletar(id);
        return ResponseEntity.ok("Deletado com sucesso");
    }

}
