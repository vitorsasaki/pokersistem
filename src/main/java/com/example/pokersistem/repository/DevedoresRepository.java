package com.example.pokersistem.repository;

import com.example.pokersistem.domain.devedores.Devedores;
import com.example.pokersistem.domain.jogador.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DevedoresRepository extends JpaRepository<Devedores, Long> {


    Optional<Devedores> findByJogador_Id(Long idJogador);
}
