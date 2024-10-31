package com.example.pokersistem.domain.Torneio;

import com.example.pokersistem.Enum.EnumSimNao;
import com.example.pokersistem.domain.tipoTorneio.TipoTorneio;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "torneio")
@Entity(name = "torneio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Torneio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_torneio", referencedColumnName = "id", nullable = true, foreignKey = @ForeignKey(name = "fk_torneioTipoTorneio"))
   // @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private TipoTorneio tipoTorneio;

    @Column(name = "numero_entradas")
    private Integer numeroEntradas;

    @Column(name = "total_buyins")
    private Integer totalBuyins;

    @Column(name = "total_rebuys")
    private Integer totalRebuys;

    @Column(name = "total_addons")
    private Integer totalAddons;

    @Column(name = "total_premiacao", precision = 10, scale = 2)
    private BigDecimal totalPremiacao;

    @Column(precision = 10, scale = 2)
    private BigDecimal rake;

    @Column(name = "fichas_cortesia", precision = 10, scale = 2)
    private BigDecimal fichasCortesia;

    @Enumerated(EnumType.STRING)
    @Column(name = "torneio_do_ranking", length = 10)
    private EnumSimNao torneioDoRanking;

    @Column(name = "data_torneio")
    private Date dataTorneio;

    @Column(name = "mes_ranking")
    private Integer mesRanking;

    @Column(name = "ano_ranking")
    private Integer anoRanking;

    public Torneio(TorneioRequestDTO data) {
        this.tipoTorneio = data.tipoTorneio();
        this.numeroEntradas = data.numeroEntradas();
        this.totalBuyins = data.totalBuyins();
        this.totalRebuys = data.totalRebuys();
        this.totalAddons = data.totalAddons();
        this.totalPremiacao = data.totalPremiacao();
        this.rake = data.rake();
        this.fichasCortesia = data.fichasCortesia();
        this.torneioDoRanking = data.torneioDoRanking();
        this.dataTorneio = data.dataTorneio();
        this.mesRanking = data.mesRanking();
        this.anoRanking = data.anoRanking();
    }
}
