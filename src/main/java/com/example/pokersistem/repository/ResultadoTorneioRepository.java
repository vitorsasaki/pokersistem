package com.example.pokersistem.repository;

import com.example.pokersistem.Enum.EnumSimNao;
import com.example.pokersistem.domain.resultadoTorneio.ResultadoTorneio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResultadoTorneioRepository extends JpaRepository<ResultadoTorneio, Long> {

    Optional<ResultadoTorneio> findByJogador_idAndTorneio_id(Long idJogador, Long idTorneio);

    List<ResultadoTorneio> findByAtualizado(EnumSimNao atualizado);
}
