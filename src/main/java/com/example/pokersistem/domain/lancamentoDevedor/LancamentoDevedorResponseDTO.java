package com.example.pokersistem.domain.lancamentoDevedor;

import com.example.pokersistem.Enum.EnumTipoLancamento;
import com.example.pokersistem.domain.jogador.Jogador;

import java.math.BigDecimal;
import java.util.Date;

public record LancamentoDevedorResponseDTO(Long id, Jogador jogador,
                                           EnumTipoLancamento tipoLancamento,
                                           BigDecimal valor,
                                           Date data) {
    public LancamentoDevedorResponseDTO(LancamentoDevedor lancamentoDevedor){
        this(lancamentoDevedor.getId(),
                lancamentoDevedor.getJogador(),
                lancamentoDevedor.getTipoLancamento(),
                lancamentoDevedor.getValor(),
                lancamentoDevedor.getData());
    }
}
