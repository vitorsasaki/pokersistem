package com.example.pokersistem.domain.devedores;

import com.example.pokersistem.domain.jogador.Jogador;

import java.math.BigDecimal;
import java.util.Date;

public record DevedoresRequestDTO(
        Jogador jogador,
        BigDecimal valor,
        Date dataUltimaAtualizacao,
        String observacao
) {
}
