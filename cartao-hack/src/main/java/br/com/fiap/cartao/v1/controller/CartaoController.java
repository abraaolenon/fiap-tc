package br.com.fiap.cartao.v1.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.cartao.core.entity.Cliente;
import br.com.fiap.cartao.core.entity.Pagamento;
import br.com.fiap.cartao.core.service.CartaoService;
import br.com.fiap.cartao.core.service.ClienteService;
import br.com.fiap.cartao.core.service.PagamentoService;
import br.com.fiap.cartao.v1.dto.ClienteDTO;
import br.com.fiap.cartao.v1.dto.GeracaoCartaoDTO;
import br.com.fiap.cartao.v1.dto.IdentificadorClienteDTO;
import br.com.fiap.cartao.v1.dto.MensagemDTO;
import br.com.fiap.cartao.v1.dto.PagamentoCartaoDTO;
import br.com.fiap.cartao.v1.dto.PagamentoCartaoRetornoDTO;
import br.com.fiap.cartao.v1.dto.IdentificadorPagamentoDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api")
@OpenAPIDefinition(info = @Info(title = "Api de Cartoes para o Hackaton"))
@Tag(name = "Cartao")
public class CartaoController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private CartaoService cartaoService;

    @Autowired
    private PagamentoService pagamentoService;

    @Operation(summary = "Registro do Cliente", description = "Registro do Cliente.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Inserido com sucesso") })
    @PostMapping(value = "/cliente", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdentificadorClienteDTO> inserirCliente(ClienteDTO dto) {

        Cliente clienteSalvo = clienteService.inserir(dto);

        return new ResponseEntity<>(new IdentificadorClienteDTO(clienteSalvo.getId()), HttpStatus.OK);

    }

    @Operation(summary = "Gerar Cartão", description = "Gerar Cartão.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Gerado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Número máximo de cartões atingido") })
    @PostMapping(value = "/cartao", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MensagemDTO> gerarCartao(GeracaoCartaoDTO dto) {

        cartaoService.gerar(dto);

        return new ResponseEntity<>(new MensagemDTO("Gerado com sucesso"), HttpStatus.OK);

    }

    @Operation(summary = "Efetuar Pagamento", description = "Efetuar Pagamento.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Efetuado com sucesso"),
            @ApiResponse(responseCode = "402", description = "O cartão do cliente estourou") })
    @PostMapping(value = "/pagamentos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdentificadorPagamentoDTO> efetuarPagamento(PagamentoCartaoDTO dto) {

        Pagamento pagamentoSalvo = pagamentoService.efetuarPagamento(dto);

        return new ResponseEntity<>(new IdentificadorPagamentoDTO(pagamentoSalvo.getId()), HttpStatus.OK);

    }

    @Operation(summary = "Consulta de Pagamentos por Cliente", description = "Consulta de Pagamentos por Cliente.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso") })
    @GetMapping(value = "/pagamentos/cliente/{idCliente}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<PagamentoCartaoRetornoDTO>> buscarPagamentosPorCliente(
            @PathVariable("idCliente") Integer idCliente) {

        return ResponseEntity.ok(pagamentoService.buscarPagamentosPorCliente(idCliente));

    }

}