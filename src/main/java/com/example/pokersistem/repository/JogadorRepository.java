package com.example.pokersistem.repository;

import com.example.pokersistem.domain.jogador.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JogadorRepository  extends JpaRepository<Jogador, Long> {

    @Query("SELECT j FROM jogador j WHERE j.idPP = :idPP")
    Optional<Jogador> findByIdPP(@Param("idPP") Long idPP);
}
