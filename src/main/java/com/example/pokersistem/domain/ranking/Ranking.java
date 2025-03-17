package com.example.pokersistem.domain.ranking;

import com.example.pokersistem.domain.jogador.Jogador;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "ranking")
@Entity(name = "ranking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Ranking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer mes;

    private Integer ano;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jogador", referencedColumnName = "id", nullable = true, foreignKey = @ForeignKey(name = "fk_rankingJogador"), unique = true)
    private Jogador jogador;

    private BigDecimal pontos;

    @Column(name = "qtd_primeiro_lugar")
    private Integer qtdPrimeiroLugar;

    @Column(name = "num_participacao")
    private Integer numParticipacao;

    public Ranking(RankingRequestDTO data) {
        this.mes = data.mes();
        this.ano = data.ano();
        this.jogador = data.jogador();
        this.pontos = data.pontos();
        this.qtdPrimeiroLugar = data.qtdPrimeiroLugar();
        this.numParticipacao = data.numParticipacao();
    }
}
