package com.example.pokersistem.domain.resultadoTorneio;

import com.example.pokersistem.domain.Torneio.Torneio;
import com.example.pokersistem.domain.jogador.Jogador;

public record ResultadoTorneioRequestDTO(
        Jogador jogador,
        Torneio torneio,
        Integer posicao,
        Integer totalPontosGanho
) {
}
