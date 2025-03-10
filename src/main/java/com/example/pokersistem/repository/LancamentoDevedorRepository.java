package com.example.pokersistem.repository;

import com.example.pokersistem.domain.lancamentoDevedor.LancamentoDevedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoDevedorRepository extends JpaRepository<LancamentoDevedor,Long> {
}
