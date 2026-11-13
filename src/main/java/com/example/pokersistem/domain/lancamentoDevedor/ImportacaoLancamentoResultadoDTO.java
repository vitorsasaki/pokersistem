package com.example.pokersistem.domain.lancamentoDevedor;

import java.util.ArrayList;
import java.util.List;

public class ImportacaoLancamentoResultadoDTO {

    private int importados;
    private List<ErroLinha> erros = new ArrayList<>();

    public int getImportados() {
        return importados;
    }

    public void setImportados(int importados) {
        this.importados = importados;
    }

    public void incrementarImportados() {
        this.importados++;
    }

    public List<ErroLinha> getErros() {
        return erros;
    }

    public void setErros(List<ErroLinha> erros) {
        this.erros = erros;
    }

    public void adicionarErro(int linha, String motivo) {
        this.erros.add(new ErroLinha(linha, motivo));
    }

    public record ErroLinha(int linha, String motivo) {
    }
}
