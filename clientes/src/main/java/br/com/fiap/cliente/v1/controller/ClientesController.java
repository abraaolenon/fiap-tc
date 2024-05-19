package br.com.fiap.cliente.v1.controller;

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

import br.com.fiap.cliente.core.service.ClienteService;
import br.com.fiap.cliente.v1.dto.ClienteDTO;
import br.com.fiap.cliente.v1.dto.ClienteRespostaDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/clientes")
@OpenAPIDefinition(info = @Info(title = "Api de Clientes para o IV Tech Challenge"))
@Tag(name = "Microsserviço de Gerenciamento de Clientes", description = "Este serviço será responsável por todas as operações relacionadas aos clientes, incluindo a criação, leitura, atualização e exclusão de registros de clientes (CRUD).")
public class ClientesController {

    @Autowired
    private ClienteService clienteService;

    @Operation(description = "Insere um cliente.")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Inserido com sucesso") })
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteRespostaDTO> inserir(ClienteDTO clienteDTO) {

        return new ResponseEntity<>(clienteService.inserir(clienteDTO), HttpStatus.CREATED);

    }

    @Operation(description = "Consulta de todos os clientes.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso") })
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ClienteRespostaDTO>> buscarTodos() throws InterruptedException {

        return ResponseEntity.ok(clienteService.buscarTodos());
    }

    @Operation(description = "Atualiza um cliente.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Atualizado com com sucesso") })
    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteRespostaDTO> atualizar(@PathVariable Integer id, ClienteDTO clienteDTO) {

        return ResponseEntity.ok(clienteService.atualizar(id, clienteDTO));
    }

    @Operation(description = "Deleta um cliente.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Deletado com sucesso") })
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deletar(@PathVariable Integer id) {

        clienteService.deletar(id);
        return ResponseEntity.ok("Deletado com sucesso");
    }

}
