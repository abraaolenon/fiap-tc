package br.com.fiap.pagamento.v1.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PagamentoRespostaDTO(Integer id, Integer codigoCompra, BigDecimal valor, String meioDePagamento, LocalDateTime dataHoraConfirmacao) {

}