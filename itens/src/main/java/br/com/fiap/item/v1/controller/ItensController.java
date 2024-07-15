package br.com.fiap.item.v1.controller;

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

import br.com.fiap.item.core.service.ItemService;
import br.com.fiap.item.v1.dto.ItemDTO;
import br.com.fiap.item.v1.dto.ItemRespostaDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/itens")
@OpenAPIDefinition(info = @Info(title = "Api de itens para o V Tech Challenge"))
@Tag(name = "Microsserviço de Gerenciamento de itens", description = "Este serviço será responsável por todas as operações relacionadas aos itens, incluindo a criação, leitura, atualização e exclusão de registros de itens (CRUD).")
public class ItensController {

    @Autowired
    private ItemService itemService;

    @Operation(description = "Insere um item.")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Inserido com sucesso") })
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemRespostaDTO> inserir(ItemDTO dto) {

        return new ResponseEntity<>(itemService.inserir(dto), HttpStatus.CREATED);

    }

    @Operation(description = "Consulta de todos os itens.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso") })
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ItemRespostaDTO>> buscarTodos() throws InterruptedException {

        return ResponseEntity.ok(itemService.buscarTodos());
    }

    @Operation(description = "Atualiza um item.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Atualizado com com sucesso") })
    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemRespostaDTO> atualizar(@PathVariable Integer id, ItemDTO dto) {

        return ResponseEntity.ok(itemService.atualizar(id, dto));
    }

    @Operation(description = "Deleta um item.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Deletado com sucesso") })
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deletar(@PathVariable Integer id) {

        itemService.deletar(id);
        return ResponseEntity.ok("Deletado com sucesso");
    }

}
