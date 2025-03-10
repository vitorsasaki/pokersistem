package com.example.pokersistem.service;

import com.example.pokersistem.Enum.EnumTipoLancamento;
import com.example.pokersistem.domain.devedores.Devedores;
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
        Optional<Devedores> devedores = devedoresService.findByIdJogador(lancamentoDevedor.getJogador());
        BigDecimal valorAtualizado = new BigDecimal("0.00");

        if(devedores.isPresent()){
            BigDecimal oldValor = devedores.get().getValor();
            BigDecimal newValor = lancamentoDevedor.getValor();
            if(lancamentoDevedor.getTipoLancamento().equals(EnumTipoLancamento.DEVE)) {
                 valorAtualizado = oldValor.add(newValor);
            }else{
                valorAtualizado = oldValor.subtract(newValor);
            }
            devedores.get().setValor(valorAtualizado);
            devedores.get().setDataUltimaAtualizacao(new Date());
            devedoresService.salvar(devedores.get());
        }else{
            Devedores newDevedor = new Devedores();
            newDevedor.setJogador(lancamentoDevedor.getJogador());
            newDevedor.setValor(lancamentoDevedor.getValor());
            newDevedor.setDataUltimaAtualizacao(new Date());
            devedoresService.salvar(newDevedor);
        }


        return lancamentoDevedorRepository.save(lancamentoDevedor);
    }

    public List<LancamentoDevedor> buscar(){
        return lancamentoDevedorRepository.findAll(Sort.by(Sort.Order.desc("id")));
    }

    public Optional<LancamentoDevedor> findById(Long id){
        return lancamentoDevedorRepository.findById(id);
    }

    public void delete(LancamentoDevedor lancamentoDevedor){
        lancamentoDevedorRepository.delete(lancamentoDevedor);
    }


}
