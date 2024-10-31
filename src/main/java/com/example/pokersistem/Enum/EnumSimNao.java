package com.example.pokersistem.Enum;

public enum EnumSimNao {

    SIM("Sim"),
    NAO("Não");

    private String enumSimNao;

    EnumSimNao(String enumSimNao){
        this.enumSimNao = enumSimNao;
    }

    public String getEnumSimNao(){
        return enumSimNao;
    }
}
