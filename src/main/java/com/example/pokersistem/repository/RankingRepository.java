package com.example.pokersistem.repository;

import com.example.pokersistem.domain.ranking.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RankingRepository extends JpaRepository<Ranking, Long> {

    Optional<Ranking> findByMesAndAnoAndJogador_id(Integer mes, Integer ano, Long idJogador);

    Optional<Ranking> findByMesAndAno(Integer mes, Integer ano);
}
