package com.example.pokersistem.domain.resultadoTorneio;

import com.example.pokersistem.Enum.EnumSimNao;
import com.example.pokersistem.domain.Torneio.Torneio;
import com.example.pokersistem.domain.jogador.Jogador;

import java.math.BigDecimal;

public record ResultadoTorneioRequestDTO(
        Jogador jogador,
        Torneio torneio,
        Integer posicao,
        BigDecimal totalPontosGanho,
        EnumSimNao pontoBonus,
        EnumSimNao atualizado
) {
}
