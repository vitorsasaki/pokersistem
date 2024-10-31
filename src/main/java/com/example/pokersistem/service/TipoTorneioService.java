package com.example.pokersistem.service;

import com.example.pokersistem.domain.jogador.Jogador;
import com.example.pokersistem.domain.tipoTorneio.TipoTorneio;
import com.example.pokersistem.repository.TipoTorneioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TipoTorneioService {

    @Autowired
    private TipoTorneioRepository repository;

    @Transactional
    public TipoTorneio salvar(TipoTorneio tipoTorneio){
        return repository.save(tipoTorneio);
    }

    public List<TipoTorneio> buscar(){
        return repository.findAll();
    }

    public Optional<TipoTorneio> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public void delete(TipoTorneio tipoTorneio){
        repository.delete(tipoTorneio);
    }
}
