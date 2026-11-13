package com.example.pokersistem.domain.jogador;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "jogador")
@Entity(name = "jogador")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Jogador {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    @Column (name = "nome_real")
    private String nomeReal;

    private String login;

    private String estado;

    @Column(name = "id_pp")
    private Long idPP;

    private String senha;

    private String celular;

    public Jogador(JogadorRequestDTO data){
        this.nome = data.nome();
        this.nomeReal = data.nomeReal();
        this.login = data.login();
        this.estado = data.estado();
        this.idPP = data.idPP();
        this.senha = data.senha();
        this.celular = data.celular();

    }



}
