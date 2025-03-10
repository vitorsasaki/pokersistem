package com.example.pokersistem.domain.pontos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "pontos")
@Entity(name = "pontos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pontos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Integer posicao;

    private Integer valor;

    @Column(name = "ponto_bonus")
    private Integer pontoBonus;

    @Column(name = "ponto_presenca")
    private Integer pontoPresenca;


}
