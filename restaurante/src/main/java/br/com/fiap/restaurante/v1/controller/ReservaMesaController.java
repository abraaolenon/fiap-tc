package br.com.fiap.restaurante.v1.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.restaurante.core.entity.ReservaMesa;
import br.com.fiap.restaurante.core.service.ReservaMesaService;
import br.com.fiap.restaurante.v1.dto.ReservaMesaDTO;
import br.com.fiap.restaurante.v1.dto.StatusMesaDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.MediaType;

@RestController
@RequestMapping("/v1/reserva-mesa")
@OpenAPIDefinition(info = @Info(title = "Api de Restaurantes para o III Tech Challenge"))
@Tag(name = "Reserva Mesa")
public class ReservaMesaController {

    @Autowired
    private ReservaMesaService reservaMesaService;

    @Operation(summary = "Cadastro de Reservas", description = " Os usuários podem fazer reservas para datas e horários específicos.")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Inserido com sucesso") })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservaMesa> inserirReservaMesa(ReservaMesaDTO reservaMesaDTO) {

        return new ResponseEntity<>(reservaMesaService.inserirReservaMesa(reservaMesaDTO), HttpStatus.CREATED);

    }

    @Operation(summary = "Consulta de Reservas", description = "Os restaurantes podem gerenciar as reservas, visualizando-as.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso") })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ReservaMesa>> buscarTodaASReservas() throws InterruptedException {

        return ResponseEntity.ok(reservaMesaService.buscarTodaASReservas());

    }

    @Operation(summary = "Atualização de Reservas", description = "Os restaurantes podem gerenciar as reservas, atualizando o status.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Atualizado com sucesso") })
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservaMesa> atualizarStatus(StatusMesaDTO statusMesaDTO) throws InterruptedException {

        return ResponseEntity.ok(reservaMesaService.atualizarStatus(statusMesaDTO));

    }

}
