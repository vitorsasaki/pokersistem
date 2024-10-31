package com.example.pokersistem.domain.jogador;

import jakarta.validation.constraints.NotBlank;

public record JogadorRequestDTO(

        @NotBlank
        String nome,
        @NotBlank
        String nomeReal,
        String login,
        String estado,
        Integer idPP,
        String senha
) {
}
