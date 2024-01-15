package br.com.fiap.parquimetro.core.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Estacionamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer idParquimetro;

    private String placaVeiculo;
    private BigDecimal valor;
    private LocalDateTime dataHoraInicioEstacionamento;
    private LocalDateTime dataHoraFimEstacionamento;

    public Estacionamento() {

    }

    public Estacionamento(Integer idParquimetro, String placaVeiculo, LocalDateTime dataHoraInicioEstacionamento) {
        this.idParquimetro = idParquimetro;
        this.placaVeiculo = placaVeiculo;
        this.dataHoraInicioEstacionamento = dataHoraInicioEstacionamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdParquimetro() {
        return idParquimetro;
    }

    public void setIdParquimetro(Integer idParquimetro) {
        this.idParquimetro = idParquimetro;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataHoraInicioEstacionamento() {
        return dataHoraInicioEstacionamento;
    }

    public void setDataHoraInicioEstacionamento(LocalDateTime dataHoraInicioEstacionamento) {
        this.dataHoraInicioEstacionamento = dataHoraInicioEstacionamento;
    }

    public LocalDateTime getDataHoraFimEstacionamento() {
        return dataHoraFimEstacionamento;
    }

    public void setDataHoraFimEstacionamento(LocalDateTime dataHoraFimEstacionamento) {
        this.dataHoraFimEstacionamento = dataHoraFimEstacionamento;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((idParquimetro == null) ? 0 : idParquimetro.hashCode());
        result = prime * result + ((placaVeiculo == null) ? 0 : placaVeiculo.hashCode());
        result = prime * result + ((valor == null) ? 0 : valor.hashCode());
        result = prime * result
                + ((dataHoraInicioEstacionamento == null) ? 0 : dataHoraInicioEstacionamento.hashCode());
        result = prime * result + ((dataHoraFimEstacionamento == null) ? 0 : dataHoraFimEstacionamento.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Estacionamento other = (Estacionamento) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (idParquimetro == null) {
            if (other.idParquimetro != null) {
                return false;
            }
        } else if (!idParquimetro.equals(other.idParquimetro)) {
            return false;
        }
        if (placaVeiculo == null) {
            if (other.placaVeiculo != null) {
                return false;
            }
        } else if (!placaVeiculo.equals(other.placaVeiculo)) {
            return false;
        }
        if (valor == null) {
            if (other.valor != null) {
                return false;
            }
        } else if (!valor.equals(other.valor)) {
            return false;
        }
        if (dataHoraInicioEstacionamento == null) {
            if (other.dataHoraInicioEstacionamento != null) {
                return false;
            }
        } else if (!dataHoraInicioEstacionamento.equals(other.dataHoraInicioEstacionamento)) {
            return false;
        }
        if (dataHoraFimEstacionamento == null) {
            if (other.dataHoraFimEstacionamento != null) {
                return false;
            }
        } else if (!dataHoraFimEstacionamento.equals(other.dataHoraFimEstacionamento)) {
            return false;
        }
        return true;
    }

}
