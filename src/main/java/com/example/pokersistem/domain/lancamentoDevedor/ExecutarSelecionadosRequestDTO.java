package com.example.pokersistem.domain.lancamentoDevedor;

import com.example.pokersistem.Enum.EnumTipoLancamento;

import java.util.List;

public record ExecutarSelecionadosRequestDTO(
        List<Long> ids,
        EnumTipoLancamento tipoLancamento
) {
}
