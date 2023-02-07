package com.br.projects.enums;

public enum StatusProjeto {
    EM_ANALISE("Em Análise"),
    ANALISE_REALIZADA("Análise Realizada"),
    ANALISE_APROVADA("Análise Aprovada"),
    INICIADO("Iniciado"),
    PLANEJADO("Planejado"),
    EM_ANDAMENTO("Em Andamento"),
    ENCERRADO("Encerrado"),
    CANCELADO("Cancelado");

    public final String status;

    private StatusProjeto(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static StatusProjeto fromString(String text) {
        for (StatusProjeto value : StatusProjeto.values()) {
            if (value.status.equalsIgnoreCase(text)) {
                return value;
            }
        }
        return null;
    }

    @Override
    public String toString(){
        return status;
    }

}
