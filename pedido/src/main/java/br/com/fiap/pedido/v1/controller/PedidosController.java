package br.com.fiap.pedido.v1.controller;

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

import br.com.fiap.pedido.core.service.PedidoService;
import br.com.fiap.pedido.v1.dto.PedidoDTO;
import br.com.fiap.pedido.v1.dto.PedidoRespostaDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/pedidos")
@OpenAPIDefinition(info = @Info(title = "Api de Pedidos para o IV Tech Challenge"))
@Tag(name = "Microsserviço de Gestão de Pedidos", description = "Centralizará o processamento de todos os pedidos, desde a criação até a conclusão. Isso inclui receber pedidos dos clientes, processar pagamentos (se aplicável), e coordenar com o microsserviço de logística de entrega para garantir a entrega eficiente dos produtos.")
public class PedidosController {

    @Autowired
    private PedidoService pedidoService;

    @Operation(description = "Insere um pedido.")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Inserido com sucesso") })
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PedidoRespostaDTO> inserir(PedidoDTO pedidoDTO) {

        return new ResponseEntity<>(pedidoService.inserir(pedidoDTO), HttpStatus.CREATED);

    }

    @Operation(description = "Consulta de todos os pedidos.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso") })
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<PedidoRespostaDTO>> buscarTodos() throws InterruptedException {

        return ResponseEntity.ok(pedidoService.buscarTodos());
    }

    @Operation(description = "Atualiza um pedido.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Atualizado com com sucesso") })
    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PedidoRespostaDTO> atualizar(@PathVariable Integer id, PedidoDTO pedidoDTO) {

        return ResponseEntity.ok(pedidoService.atualizar(id, pedidoDTO));
    }

    @Operation(description = "Deleta um pedido.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Deletado com sucesso") })
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deletar(@PathVariable Integer id) {

        pedidoService.deletar(id);
        return ResponseEntity.ok("Deletado com sucesso");
    }

    @Operation(description = "Concluir um pedido.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Concluído com sucesso") })
    @PutMapping(path = "/conclusao/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PedidoRespostaDTO> conclusao(@PathVariable Integer id) {

        return ResponseEntity.ok(pedidoService.conclusao(id));
    }

    @Operation(description = "Processar Pagamento.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Processado com sucesso") })
    @PutMapping(path = "/processar-pagamento/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PedidoRespostaDTO> pagamento(@PathVariable Integer id) {

        return ResponseEntity.ok(pedidoService.pagamento(id));
    }

}
