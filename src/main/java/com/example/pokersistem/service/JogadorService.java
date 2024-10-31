package com.example.pokersistem.service;

import com.example.pokersistem.domain.jogador.Jogador;
import com.example.pokersistem.domain.jogador.JogadorRequestDTO;
import com.example.pokersistem.repository.JogadorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JogadorService {

    @Autowired
    private JogadorRepository repository;


    @Transactional
    public Jogador salvar(Jogador jogador){
        return repository.save(jogador);
    }

    public List<Jogador> buscar(){
        return repository.findAll();
    }

    public Optional<Jogador> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public void delete(Jogador jogador){
        repository.delete(jogador);
    }
}
