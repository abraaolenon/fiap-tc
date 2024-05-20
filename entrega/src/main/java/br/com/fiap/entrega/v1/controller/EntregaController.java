package br.com.fiap.entrega.v1.controller;

import java.time.LocalDate;
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

import br.com.fiap.entrega.core.service.EntregaService;
import br.com.fiap.entrega.v1.dto.EnderecoDTO;
import br.com.fiap.entrega.v1.dto.EntregaDTO;
import br.com.fiap.entrega.v1.dto.EntregaRespostaDTO;
import br.com.fiap.entrega.v1.dto.EntregadorDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/entregas")
@OpenAPIDefinition(info = @Info(title = "Api de Entregas para o IV Tech Challenge"))
@Tag(name = "Microsserviço de Logística de Entrega", description = "Este serviço cuidará de toda a logística relacionada à entrega de pedidos, desde a atribuição de entregadores até o rastreamento das entregas em tempo real. A função inclui calcular as rotas mais eficientes, estimar tempos de entrega e fornecer atualizações de status aos clientes.")
public class EntregaController {

    @Autowired
    private EntregaService entregaService;

    @Operation(description = "Consulta de todas as entregas.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso") })
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<EntregaRespostaDTO>> buscarTodos() throws InterruptedException {

        return ResponseEntity.ok(entregaService.buscarTodos());
    }

    @Operation(description = "Consulta de uma entrega para visualizar o status e a data estimada de entrega.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso") })
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntregaRespostaDTO> buscar(@PathVariable Integer id) throws InterruptedException {

        return ResponseEntity.ok(entregaService.buscar(id));
    }

    @Operation(description = "Insere uma entrega.")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Inserido com sucesso") })
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntregaRespostaDTO> inserir(EntregaDTO entregaDTO) {

        return new ResponseEntity<>(entregaService.inserir(entregaDTO), HttpStatus.CREATED);

    }

    @Operation(description = "Atribuição de entregadores para uma entrega.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Atualizado com com sucesso") })
    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntregaRespostaDTO> atualizar(@PathVariable Integer id, EntregadorDTO entregadorDTO) {

        return ResponseEntity.ok(entregaService.atualizarEntregador(id, entregadorDTO));
    }

    @Operation(description = "Deleta uma entrega.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Deletado com sucesso") })
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deletar(@PathVariable Integer id) {

        entregaService.deletar(id);
        return ResponseEntity.ok("Deletado com sucesso");
    }

    @Operation(description = "Consulta a data estimada de entrega para um endereço.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso") })
    @GetMapping(path = "/data-entrega", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LocalDate> dataEntrega(EnderecoDTO enderecoDTO) throws InterruptedException {

        return ResponseEntity.ok(entregaService.calcularDataEntrega(enderecoDTO.endereco()));
    }

    @Operation(description = "Consulta a rota mais eficiente de entrega para um endereço.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso") })
    @GetMapping(path = "/rota", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<String>> rotaEntrega(EnderecoDTO enderecoDTO) throws InterruptedException {

        return ResponseEntity.ok(entregaService.buscarRotaEntrega(enderecoDTO.endereco()));
    }


}
