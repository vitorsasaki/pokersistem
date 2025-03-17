package com.example.pokersistem.domain.ranking;

import com.example.pokersistem.domain.jogador.Jogador;

import java.math.BigDecimal;

public record RankingResponseDTO( Long id, Integer mes,
                                         Integer ano,
                                         Jogador jogador,
                                         BigDecimal pontos,
                                         Integer qtdPrimeiroLugar,
                                         Integer numParticipacao) {
    public RankingResponseDTO(Ranking ranking){
        this(ranking.getId(),
                ranking.getMes(),
                ranking.getAno(),
                ranking.getJogador(),
                ranking.getPontos(),
                ranking.getQtdPrimeiroLugar(),
                ranking.getNumParticipacao());
    }
}
