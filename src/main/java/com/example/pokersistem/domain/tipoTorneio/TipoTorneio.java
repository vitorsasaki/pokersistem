package com.example.pokersistem.domain.tipoTorneio;

import com.example.pokersistem.Enum.EnumDiaSemana;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "tipo_torneio")
@Entity(name = "tipo_torneio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TipoTorneio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(name = "registro_tardio")
    private Integer registroTardio;

    @Enumerated(EnumType.STRING)
    @Column(name = "dia_semana")
    private EnumDiaSemana diaSemana;

    @Column(name = "stack_inicial")
    private String stackInicial;

    @Column(name = "stack_rebuy")
    private String stackRebuy;

    @Column(name = "stack_addon")
    private String stackAddon;

    @Column(name = "valor_rebuy", precision = 10, scale = 2)
    private BigDecimal valorRebuy;

    @Column(name = "valor_addon", precision = 10, scale = 2)
    private BigDecimal valorAddon;

    public TipoTorneio(TipoTorneioRequestDTO data){
        this.valor = data.valor();
        this.registroTardio = data.registroTardio();
        this.diaSemana = data.diaSemana();
        this.stackInicial = data.stackInicial();
        this.stackRebuy = data.stackRebuy();
        this.stackAddon = data.stackAddon();
        this.valorRebuy = data.valorRebuy();
        this.valorAddon = data.valorAddon();
    }

}
