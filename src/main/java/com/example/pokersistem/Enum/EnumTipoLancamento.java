package com.example.pokersistem.Enum;

public enum EnumTipoLancamento {

    DEVE("deve"),
    ABATER("abater");

    private String enumTipoLancamento;

    EnumTipoLancamento(String enumTipoLancamento){this.enumTipoLancamento = enumTipoLancamento;}

    public String getEnumTipoLancamento(){return enumTipoLancamento;}
}
