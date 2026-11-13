package com.example.pokersistem.domain.jogador;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;

public record JogadorRequestDTO(

        @NotBlank
        String nome,
        @NotBlank
        String nomeReal,
        String login,
        String estado,
        @JsonAlias("idpp")
        Long idPP,
        String senha,
        String celular
) {
}
