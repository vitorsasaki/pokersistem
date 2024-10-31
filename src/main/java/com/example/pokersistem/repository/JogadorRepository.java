package com.example.pokersistem.repository;

import com.example.pokersistem.domain.jogador.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogadorRepository  extends JpaRepository<Jogador, Long> {
}
