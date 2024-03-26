package br.com.fiap.restaurante.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.restaurante.core.entity.Restaurante;
import br.com.fiap.restaurante.core.repository.RestauranteRepository;
import br.com.fiap.restaurante.v1.dto.ChaveBuscaRestauranteDTO;
import br.com.fiap.restaurante.v1.dto.RestauranteDTO;

@Service
public class RestauranteService {

        @Autowired
        private RestauranteRepository restauranteRepository;

        public RestauranteDTO inserirRestaurante(RestauranteDTO restauranteDTO) {

                Restaurante restaurante = restauranteRepository
                                .save(new Restaurante(restauranteDTO));

                return toDTO(restaurante);
        }

        public List<RestauranteDTO> buscarRestaurante(ChaveBuscaRestauranteDTO chaveBuscaRestauranteDTO)
                        throws InterruptedException {

                List<RestauranteDTO> retorno = new ArrayList<>();

                restauranteRepository
                                .findByNomeOrLocalizacaoOrTipoCozinha(chaveBuscaRestauranteDTO.nome(),
                                                chaveBuscaRestauranteDTO.localizacao(),
                                                chaveBuscaRestauranteDTO.tipoCozinha())
                                .forEach(e -> retorno.add(toDTO(e)));

                return retorno;
        }

        private RestauranteDTO toDTO(Restaurante restaurante) {
                return new RestauranteDTO(
                                restaurante.getNome(),
                                restaurante.getLocalizacao(),
                                restaurante.getTipoCozinha(),
                                restaurante.getHorarios(),
                                restaurante.getCapacidade());
        }

}
