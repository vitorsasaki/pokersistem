package com.example.pokersistem.domain.devedores;

import com.example.pokersistem.domain.jogador.Jogador;

import java.math.BigDecimal;
import java.util.Date;

public record DevedoresResponseDTO(Long id, Jogador jogador,
                                   BigDecimal valor,
                                   Date dataUltimaAtualizacao,
                                   String observacao) {
    public DevedoresResponseDTO(Devedores devedores){
        this(devedores.getId(),
                devedores.getJogador(),
                devedores.getValor(),
                devedores.getDataUltimaAtualizacao(),
                devedores.getObservacao());
    }
}
