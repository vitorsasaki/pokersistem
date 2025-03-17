package com.example.pokersistem.domain.ranking;

import com.example.pokersistem.domain.jogador.Jogador;

import java.math.BigDecimal;

public record RankingRequestDTO(
        Integer mes,
        Integer ano,
        Jogador jogador,
        BigDecimal pontos,
        Integer qtdPrimeiroLugar,
        Integer numParticipacao
) {
}
