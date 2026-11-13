package com.example.pokersistem.Enum;

public enum EnumTipoLancamento {

    DEVE("deve"),
    ABATER("abater"),
    APROVAR("aprovar"),
    PAGO("pago"),
    SAQUE("saque");

    private String enumTipoLancamento;

    EnumTipoLancamento(String enumTipoLancamento){this.enumTipoLancamento = enumTipoLancamento;}

    public String getEnumTipoLancamento(){return enumTipoLancamento;}

    public static EnumTipoLancamento fromPlanilha(String valor) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("Tipo de operação vazio");
        }
        String normalizado = valor.trim().toUpperCase()
                .replace("Á", "A")
                .replace("Ã", "A")
                .replace("É", "E")
                .replace("Í", "I")
                .replace("Ó", "O")
                .replace("Ú", "U")
                .replace("Ç", "C");
        for (EnumTipoLancamento tipo : values()) {
            if (tipo.name().equals(normalizado) || tipo.enumTipoLancamento.equalsIgnoreCase(valor.trim())) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de operação não suportado: " + valor);
    }
}
