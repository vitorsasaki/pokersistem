package com.example.pokersistem.domain.resultadoTorneio;

import com.example.pokersistem.Enum.EnumSimNao;
import com.example.pokersistem.domain.Torneio.Torneio;
import com.example.pokersistem.domain.jogador.Jogador;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "resultado_torneio")
@Entity(name = "resultado_torneio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ResultadoTorneio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jogador", referencedColumnName = "id", nullable = true, foreignKey = @ForeignKey(name = "fk_resultadoTorneioJogador"), unique = true)
    private Jogador jogador;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_torneio", referencedColumnName = "id", nullable = true, foreignKey = @ForeignKey(name = "fk_resultadoTorneioTorneio"))
    private Torneio torneio;

    private Integer posicao;

    private BigDecimal totalPontosGanho;

    @Enumerated(EnumType.STRING)
    private EnumSimNao pontoBonus;

    public ResultadoTorneio(ResultadoTorneioRequestDTO data){
        this.jogador = data.jogador();
        this.torneio = data.torneio();
        this.posicao = data.posicao();
        this.totalPontosGanho = data.totalPontosGanho();
        this.pontoBonus = data.pontoBonus();
    }


}
