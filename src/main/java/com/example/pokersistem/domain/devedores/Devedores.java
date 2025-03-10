package com.example.pokersistem.domain.devedores;

import com.example.pokersistem.domain.jogador.Jogador;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "devedores")
@Entity(name = "devedores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Devedores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jogador", referencedColumnName = "id", nullable = true, foreignKey = @ForeignKey(name = "fk_devedoresJogador"), unique = true)
    private Jogador jogador;

    private BigDecimal valor;

    @Column(name = "data_ultima_atualizacao")
    private Date dataUltimaAtualizacao;

    private String observacao;

    public Devedores(DevedoresRequestDTO data){
        this.jogador = data.jogador();
        this.valor = data.valor();
        this.dataUltimaAtualizacao = data.dataUltimaAtualizacao();
        this.observacao = data.observacao();
    }


}
