package com.example.pokersistem.service;

import com.example.pokersistem.domain.devedores.Devedores;
import com.example.pokersistem.domain.jogador.Jogador;
import com.example.pokersistem.repository.DevedoresRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DevedoresService {

    @Autowired
    private DevedoresRepository devedoresRepository;

    @Transactional
    public Devedores salvar(Devedores devedores){
        return devedoresRepository.save(devedores);
    }

    public List<Devedores> buscar(){
        return devedoresRepository.findAll(Sort.by(Sort.Order.desc("dataUltimaAtualizacao")));
    }

    public Optional<Devedores> findById(Long id){
        return devedoresRepository.findById(id);
    }

    public void delete(Devedores devedores){
        devedoresRepository.delete(devedores);
    }

    public Optional<Devedores> findByIdJogador(Jogador jogador){
        return devedoresRepository.findByJogador_Id(jogador.getId());
    }
}
