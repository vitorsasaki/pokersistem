package com.example.pokersistem.domain.jogador;

public record JogadorResponseDTO(Long id, String nome, String nomeReal, String login, String estado, Integer idPP, String senha) {
    public JogadorResponseDTO(Jogador jogador){
        this(jogador.getId(),
                jogador.getNome(),
                jogador.getNomeReal(),
                jogador.getLogin(),
                jogador.getEstado(),
                jogador.getIdPP(),
                jogador.getSenha());
    }
}
