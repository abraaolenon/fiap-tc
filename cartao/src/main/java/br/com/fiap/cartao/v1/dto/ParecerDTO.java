package br.com.fiap.cartao.v1.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ParecerDTO(
        Integer id,
        String nome,
        BigDecimal renda,
        String temCadastroNegativado,
        LocalDate dataCadastro,
        BigDecimal limite,
        String parecer) {

}