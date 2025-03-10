package com.example.pokersistem.domain.lancamentoDevedor;

import com.example.pokersistem.Enum.EnumTipoLancamento;
import com.example.pokersistem.domain.jogador.Jogador;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Table(name = "lancamento_devedor")
@Entity(name = "lancamento_devedor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class LancamentoDevedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jogador", referencedColumnName = "id", nullable = true, foreignKey = @ForeignKey(name = "fk_lancamentoDevedorJogador"))
    private Jogador jogador;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_lancamento")
    private EnumTipoLancamento tipoLancamento;

    private BigDecimal valor;

    private Date data;

    public LancamentoDevedor(LancamentoDevedorRequestDTO data){
        this.jogador = data.jogador();
        this.tipoLancamento = data.tipoLancamento();
        this.valor = data.valor();
        this.data = data.data();
    }

}
