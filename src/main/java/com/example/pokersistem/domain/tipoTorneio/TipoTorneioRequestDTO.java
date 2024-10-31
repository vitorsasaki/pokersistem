package com.example.pokersistem.domain.tipoTorneio;

import com.example.pokersistem.Enum.EnumDiaSemana;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record TipoTorneioRequestDTO(

        BigDecimal valor,

        Integer registroTardio,

        EnumDiaSemana diaSemana,

        String stackInicial,

        String stackRebuy,

        String stackAddon,

        BigDecimal valorRebuy,

        BigDecimal valorAddon

) {
}
