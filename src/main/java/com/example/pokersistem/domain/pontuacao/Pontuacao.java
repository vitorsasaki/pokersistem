package com.example.pokersistem.domain.pontuacao;

import com.example.pokersistem.domain.Torneio.Torneio;
import com.example.pokersistem.domain.jogador.Jogador;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "pontuacao")
@Table(name = "pontuacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pontuacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jogador", referencedColumnName = "id", nullable = true, foreignKey = @ForeignKey(name = "fk_pontuacaoJogador"))
    private Jogador jogador;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_torneio", referencedColumnName = "id", nullable = true, foreignKey = @ForeignKey(name = "fk_pontuacaoTorneio"))
    private Torneio torneio;

    @Column(name = "colocacao")
    private Integer colocacao;

    @Column(name = "ponto_bonus")
    private Integer pontoBonus;

    @Column(name = "ponto_presenca")
    private Integer pontoPresenca;

    @Column(name = "pontos_ganho")
    private Integer pontoGanho;
}
