package com.example.pokersistem.repository;

import com.example.pokersistem.domain.Torneio.Torneio;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TorneioRepository extends JpaRepository<Torneio, Long> {

    @EntityGraph(attributePaths = {"tipoTorneio"})
    Optional<Torneio> findById(Long id);
}
