package com.example.pokersistem.service;

import com.example.pokersistem.domain.resultadoTorneio.ResultadoTorneio;
import com.example.pokersistem.repository.ResultadoTorneioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResultadoTorneioService {

    @Autowired
    private ResultadoTorneioRepository resultadoTorneioRepository;

    @Transactional
    public ResultadoTorneio salvar(ResultadoTorneio resultadoTorneio){
        return resultadoTorneioRepository.save(resultadoTorneio);
    }

    public List<ResultadoTorneio> buscar(){
        return resultadoTorneioRepository.findAll();
    }

    public Optional<ResultadoTorneio> findById(Long id){
        return resultadoTorneioRepository.findById(id);
    }

    public void delete(ResultadoTorneio resultadoTorneio){
        resultadoTorneioRepository.delete(resultadoTorneio);
    }


}
