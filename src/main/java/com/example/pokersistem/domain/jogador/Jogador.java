package com.example.pokersistem.domain.jogador;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "jogador")
@Entity(name = "jogador")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Jogador {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    @Column (name = "nome_real")
    private String nomeReal;

    private String login;

    private String estado;

    private int idPP;

    private String senha;

    public Jogador(JogadorRequestDTO data){
        this.nome = data.nome();
        this.nomeReal = data.nomeReal();
        this.login = data.login();
        this.estado = data.estado();
        this.idPP = data.idPP();
        this.senha = data.senha();

    }



}
