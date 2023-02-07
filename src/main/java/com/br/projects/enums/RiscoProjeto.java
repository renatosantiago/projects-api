package com.br.projects.enums;

public enum RiscoProjeto {
    BAIXO("Baixo"),
    MEDIO("Médio"),
    ALTO("Alto");

    public final String risco;

    private RiscoProjeto(String risco) {
        this.risco = risco;
    }

    public String getRisco() { return this.risco; }

    public static RiscoProjeto fromString(String text) {
        for (RiscoProjeto value : RiscoProjeto.values()) {
            if (value.risco.equalsIgnoreCase(text)) {
                return value;
            }
        }
        return null;
    }

    @Override
    public String toString(){
        return risco;
    }

}
