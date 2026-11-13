package com.example.pokersistem.domain.jogador;

public record JogadorResponseDTO(Long id, String nome, String nomeReal, String login, String estado, Long idPP, String senha, String celular) {
    public JogadorResponseDTO(Jogador jogador){
        this(jogador.getId(),
                jogador.getNome(),
                jogador.getNomeReal(),
                jogador.getLogin(),
                jogador.getEstado(),
                jogador.getIdPP(),
                jogador.getSenha(),
                jogador.getCelular());
    }
}
