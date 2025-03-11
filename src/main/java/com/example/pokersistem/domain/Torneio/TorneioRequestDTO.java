package com.example.pokersistem.domain.Torneio;

import com.example.pokersistem.Enum.EnumSimNao;
import com.example.pokersistem.domain.tipoTorneio.TipoTorneio;

import java.math.BigDecimal;
import java.util.Date;

public record TorneioRequestDTO(
        Integer anoRanking,
        Date dataTorneio,
        BigDecimal fichasCortesia,
        Integer mesRanking,
        Integer numeroEntradas,
        BigDecimal rake,
        EnumSimNao torneioDoRanking,
        Integer totalAddons,
        Integer totalBuyins,
        BigDecimal totalPremiacao,
        Integer totalRebuys,
        TipoTorneio tipoTorneio,
        BigDecimal peso
) {
}
