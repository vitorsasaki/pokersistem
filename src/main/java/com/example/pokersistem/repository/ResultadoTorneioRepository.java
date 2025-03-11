package com.example.pokersistem.repository;

import com.example.pokersistem.domain.resultadoTorneio.ResultadoTorneio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResultadoTorneioRepository extends JpaRepository<ResultadoTorneio, Long> {

    Optional<ResultadoTorneio> findByJogador_id(Long idJogador);
}
