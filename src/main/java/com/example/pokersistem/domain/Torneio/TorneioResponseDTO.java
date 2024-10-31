package com.example.pokersistem.domain.Torneio;


import com.example.pokersistem.Enum.EnumSimNao;
import com.example.pokersistem.domain.tipoTorneio.TipoTorneio;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.util.Date;


public record TorneioResponseDTO(Long id, Integer anoRanking, Date dataTorneio,
                                 BigDecimal fichasCortesia, Integer mesRanking,
                                 Integer numeroEntradas, BigDecimal rake,
                                 EnumSimNao torneioDoRanking, Integer totalAddons,
                                 Integer totalBuyins, BigDecimal totalPremiacao, Integer totalRebuys, TipoTorneio tipoTorneio) {

    public TorneioResponseDTO(Torneio torneio){
        this(torneio.getId(),
                torneio.getAnoRanking(),
                torneio.getDataTorneio(),
                torneio.getFichasCortesia(),
                torneio.getMesRanking(),
                torneio.getNumeroEntradas(),
                torneio.getRake(),
                torneio.getTorneioDoRanking(),
                torneio.getTotalAddons(),
                torneio.getTotalBuyins(),
                torneio.getTotalPremiacao(),
                torneio.getTotalRebuys(),
                torneio.getTipoTorneio());
    }


}
