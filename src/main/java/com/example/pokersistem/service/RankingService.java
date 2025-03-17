package com.example.pokersistem.service;

import com.example.pokersistem.Enum.EnumSimNao;
import com.example.pokersistem.domain.ranking.Ranking;
import com.example.pokersistem.domain.resultadoTorneio.ResultadoTorneio;
import com.example.pokersistem.repository.RankingRepository;
import com.example.pokersistem.repository.ResultadoTorneioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RankingService {

    @Autowired
    private RankingRepository repository;

    @Autowired
    private ResultadoTorneioRepository resultadoTorneioRepository;


    @Transactional
    public Ranking salvar(Ranking ranking){
        return repository.save(ranking);
    }

    public void atualizarRanking(List<ResultadoTorneio> listaResultadoTorneio){

        for (ResultadoTorneio resultadoTorneio : listaResultadoTorneio){
            Optional<Ranking> ranking = repository.findByMesAndAnoAndJogador_id(resultadoTorneio.getTorneio().getMesRanking(),
                                                                                          resultadoTorneio.getTorneio().getAnoRanking(),
                                                                                          resultadoTorneio.getJogador().getId());
            if(ranking.isPresent()){
                ranking.get().setPontos(ranking.get().getPontos().add(resultadoTorneio.getTotalPontosGanho()));
                ranking.get().setNumParticipacao(ranking.get().getNumParticipacao() + 1);
                if(resultadoTorneio.getPosicao().equals(1)){
                    ranking.get().setQtdPrimeiroLugar(ranking.get().getQtdPrimeiroLugar() + 1);
                }
                repository.save(ranking.get());
            }else {
                   Ranking novoRanking = new Ranking();
                   novoRanking.setMes(resultadoTorneio.getTorneio().getMesRanking());
                   novoRanking.setAno(resultadoTorneio.getTorneio().getAnoRanking());
                   novoRanking.setJogador(resultadoTorneio.getJogador());
                   novoRanking.setPontos(resultadoTorneio.getTotalPontosGanho());
                   novoRanking.setNumParticipacao(1);
                   novoRanking.setQtdPrimeiroLugar(0);
                   if (resultadoTorneio.getPosicao().equals(1))
                       novoRanking.setQtdPrimeiroLugar(1);
                   repository.save(novoRanking);
            }

            resultadoTorneio.setAtualizado(EnumSimNao.SIM);
            resultadoTorneioRepository.save(resultadoTorneio);


        }
    }

    public List<Ranking> buscar(){
        return repository.findAll(Sort.by(Sort.Order.desc("pontos"),Sort.Order.desc("qtdPrimeiroLugar")));

    }

    public Optional<Ranking> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public void delete(Ranking ranking){
        repository.delete(ranking);
    }
}
