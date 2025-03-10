package com.example.pokersistem.domain.resultadoTorneio;

import com.example.pokersistem.domain.Torneio.Torneio;
import com.example.pokersistem.domain.jogador.Jogador;

public record ResultadoTorneioResponseDTO(Long id,
                                          Jogador jogador,
                                          Torneio torneio,
                                          Integer posicao,
                                          Integer totalPontosGanho) {
    public ResultadoTorneioResponseDTO(ResultadoTorneio resultadoTorneio){
        this(resultadoTorneio.getId(),
                resultadoTorneio.getJogador(),
                resultadoTorneio.getTorneio(),
                resultadoTorneio.getPosicao(),
                resultadoTorneio.getTotalPontosGanho());
    }
}
