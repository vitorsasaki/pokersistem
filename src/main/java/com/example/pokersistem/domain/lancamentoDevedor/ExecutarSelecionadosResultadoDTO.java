package com.example.pokersistem.domain.lancamentoDevedor;

public class ExecutarSelecionadosResultadoDTO {

    private int atualizados;
    private int erros;

    public ExecutarSelecionadosResultadoDTO() {
    }

    public ExecutarSelecionadosResultadoDTO(int atualizados, int erros) {
        this.atualizados = atualizados;
        this.erros = erros;
    }

    public int getAtualizados() {
        return atualizados;
    }

    public void setAtualizados(int atualizados) {
        this.atualizados = atualizados;
    }

    public int getErros() {
        return erros;
    }

    public void setErros(int erros) {
        this.erros = erros;
    }
}
