package com.example.pokersistem.service;

import com.example.pokersistem.domain.Torneio.Torneio;
import com.example.pokersistem.domain.Torneio.TorneioResponseDTO;
import com.example.pokersistem.domain.jogador.Jogador;
import com.example.pokersistem.repository.TorneioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TorneioService {

    @Autowired
    private TorneioRepository torneioRepository;

    @Transactional
    public Torneio salvar(Torneio torneio){
        return torneioRepository.save(torneio);
    }

    public List<Torneio> buscar(){
        return torneioRepository.findAll();
    }

    public TorneioResponseDTO buscarTorneioPorId(Long id){
        Optional<Torneio> torneio = torneioRepository.findById(id);
        TorneioResponseDTO torneioResponseDTO = new TorneioResponseDTO(torneio.get());
        return torneioResponseDTO;
    }


    public Optional<Torneio> findById(Long id) {
        return torneioRepository.findById(id);
    }

    @Transactional
    public void delete(Torneio torneio){
        torneioRepository.delete(torneio);
    }
}
