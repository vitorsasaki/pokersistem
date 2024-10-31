package com.example.pokersistem.domain.tipoTorneio;

import com.example.pokersistem.Enum.EnumDiaSemana;

import java.math.BigDecimal;

public record TipoTorneioResponseDTO(Long id, BigDecimal valor, Integer registroTardio, EnumDiaSemana diaSemana, String stackInicial, String stackRebuy,
                                     String stackAddon, BigDecimal valorRebuy, BigDecimal valorAddon) {

    public TipoTorneioResponseDTO(TipoTorneio tipoTorneio){
        this( tipoTorneio.getId(),
                tipoTorneio.getValor(),
                tipoTorneio.getRegistroTardio(),
                tipoTorneio.getDiaSemana(),
                tipoTorneio.getStackInicial(),
                tipoTorneio.getStackRebuy(),
                tipoTorneio.getStackAddon(),
                tipoTorneio.getValorRebuy(),
                tipoTorneio.getValorAddon());
    }
}
