package com.example.pokersistem.Enum;

public enum EnumDiaSemana {
    SEGUNDA("Segunda-Feira"),
    TERCA("Terça-Feira"),
    QUARTA("Quarta-Feira"),
    QUINTA("Quinta-Feira"),
    SEXTA("Sexta-Feira"),
    SABADO("Sabado"),
    DOMINGO("Domingo");

    private String diaSemana;

    EnumDiaSemana(String diaSemana){
        this.diaSemana = diaSemana;
    }

    public String getDiaSemana(){
        return diaSemana;
    }
}
