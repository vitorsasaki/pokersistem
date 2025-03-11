package com.example.pokersistem.service;

import com.example.pokersistem.Enum.EnumSimNao;
import com.example.pokersistem.domain.jogador.Jogador;
import com.example.pokersistem.domain.pontos.Pontos;
import com.example.pokersistem.domain.resultadoTorneio.ResultadoTorneio;
import com.example.pokersistem.repository.JogadorRepository;
import com.example.pokersistem.repository.PontosRepository;
import com.example.pokersistem.repository.ResultadoTorneioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResultadoTorneioService {

    @Autowired
    private ResultadoTorneioRepository resultadoTorneioRepository;

    @Autowired
    private PontosRepository pontosRepository;

    @Autowired
    private JogadorRepository jogadorRepository;

    @Transactional
    public ResultadoTorneio salvar(ResultadoTorneio resultadoTorneio){
        Optional<ResultadoTorneio> jogadorCadastrado = resultadoTorneioRepository.findByJogador_id(resultadoTorneio.getJogador().getId());

        if(jogadorCadastrado.isPresent())
            throw new RuntimeException("Esse jogador ja foi cadastrado a posição dele nesse torneio!");

        resultadoTorneio.setTotalPontosGanho(calculaPontuacaoJogador(resultadoTorneio));

        return resultadoTorneioRepository.save(resultadoTorneio);
    }

    public ResultadoTorneio update(ResultadoTorneio resultadoTorneio){
        resultadoTorneio.setTotalPontosGanho(calculaPontuacaoJogador(resultadoTorneio));
        return resultadoTorneioRepository.save(resultadoTorneio);
    }

    public List<ResultadoTorneio> buscar(){
        return resultadoTorneioRepository.findAll(Sort.by(Sort.Order.asc("torneio"), Sort.Order.asc("posicao")));
    }

    public Optional<ResultadoTorneio> findById(Long id){
        return resultadoTorneioRepository.findById(id);
    }

    public void delete(ResultadoTorneio resultadoTorneio){
        resultadoTorneioRepository.delete(resultadoTorneio);
    }

    public BigDecimal calculaPontuacaoJogador(ResultadoTorneio resultadoTorneio){
        Optional<Pontos> pontos = pontosRepository.findByPosicao(resultadoTorneio.getPosicao());
        BigDecimal valorPonto = BigDecimal.ZERO;
        valorPonto = (new BigDecimal(pontos.get().getValor()).multiply(resultadoTorneio.getTorneio().getPeso())).add(new BigDecimal(pontos.get().getPontoPresenca()) );

        if(resultadoTorneio.getPontoBonus().equals(EnumSimNao.SIM))
            valorPonto = valorPonto.add(new BigDecimal(pontos.get().getPontoBonus()));

        return valorPonto;
    }


}
