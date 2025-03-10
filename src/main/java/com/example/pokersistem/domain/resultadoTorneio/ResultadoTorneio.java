package com.example.pokersistem.domain.resultadoTorneio;

import com.example.pokersistem.domain.Torneio.Torneio;
import com.example.pokersistem.domain.jogador.Jogador;
import jakarta.persistence.*;
import lombok.*;

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
    @JoinColumn(name = "id_torneio", referencedColumnName = "id", nullable = true, foreignKey = @ForeignKey(name = "fk_resultadoTorneioTorneio"), unique = true)
    private Torneio torneio;

    private Integer posicao;

    private Integer totalPontosGanho;

    public ResultadoTorneio(ResultadoTorneioRequestDTO data){
        this.jogador = data.jogador();
        this.torneio = data.torneio();
        this.posicao = data.posicao();
        this.totalPontosGanho = data.totalPontosGanho();
    }


}
