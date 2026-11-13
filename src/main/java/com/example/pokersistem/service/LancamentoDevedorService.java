package com.example.pokersistem.service;

import com.example.pokersistem.Enum.EnumTipoLancamento;
import com.example.pokersistem.domain.devedores.Devedores;
import com.example.pokersistem.domain.lancamentoDevedor.ExecutarSelecionadosResultadoDTO;
import com.example.pokersistem.domain.lancamentoDevedor.LancamentoDevedor;
import com.example.pokersistem.repository.LancamentoDevedorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LancamentoDevedorService {

    @Autowired
    private LancamentoDevedorRepository lancamentoDevedorRepository;

    @Autowired DevedoresService devedoresService;

    @Transactional
    public LancamentoDevedor salvar(LancamentoDevedor lancamentoDevedor){
        EnumTipoLancamento tipo = lancamentoDevedor.getTipoLancamento();

        if (tipo == EnumTipoLancamento.DEVE || tipo == EnumTipoLancamento.ABATER) {
            Optional<Devedores> devedores = devedoresService.findByIdJogador(lancamentoDevedor.getJogador());

            if (devedores.isPresent()) {
                BigDecimal oldValor = devedores.get().getValor();
                BigDecimal newValor = lancamentoDevedor.getValor();
                BigDecimal valorAtualizado;

                if (tipo == EnumTipoLancamento.DEVE) {
                    valorAtualizado = oldValor.add(newValor);
                } else {
                    valorAtualizado = oldValor.subtract(newValor);
                }

                devedores.get().setValor(valorAtualizado);
                devedores.get().setDataUltimaAtualizacao(new Date());
                devedoresService.salvar(devedores.get());
            } else if (tipo == EnumTipoLancamento.DEVE) {
                Devedores newDevedor = new Devedores();
                newDevedor.setJogador(lancamentoDevedor.getJogador());
                newDevedor.setValor(lancamentoDevedor.getValor());
                newDevedor.setDataUltimaAtualizacao(new Date());
                devedoresService.salvar(newDevedor);
            }
        }

        return lancamentoDevedorRepository.save(lancamentoDevedor);
    }

    public List<LancamentoDevedor> buscar(){
        return lancamentoDevedorRepository.findAll(
                Sort.by(Sort.Order.desc("data"), Sort.Order.desc("id"))
        );
    }

    public Optional<LancamentoDevedor> findById(Long id){
        return lancamentoDevedorRepository.findById(id);
    }

    public void delete(LancamentoDevedor lancamentoDevedor){
        lancamentoDevedorRepository.delete(lancamentoDevedor);
    }

    @Transactional
    public ExecutarSelecionadosResultadoDTO executarSelecionados(List<Long> ids, EnumTipoLancamento tipoLancamento) {
        int atualizados = 0;
        int erros = 0;

        if (ids == null || ids.isEmpty() || tipoLancamento == null) {
            return new ExecutarSelecionadosResultadoDTO(0, 0);
        }

        for (Long id : ids) {
            try {
                Optional<LancamentoDevedor> optional = lancamentoDevedorRepository.findById(id);
                if (optional.isEmpty()) {
                    erros++;
                    continue;
                }
                LancamentoDevedor lancamento = optional.get();
                lancamento.setTipoLancamento(tipoLancamento);
                salvar(lancamento);
                atualizados++;
            } catch (Exception e) {
                erros++;
            }
        }

        return new ExecutarSelecionadosResultadoDTO(atualizados, erros);
    }

}
