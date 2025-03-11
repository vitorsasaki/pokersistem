package com.example.pokersistem.repository;

import com.example.pokersistem.domain.pontos.Pontos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PontosRepository extends JpaRepository<Pontos, Long> {

    Optional<Pontos> findByPosicao(Integer posicao);
}
