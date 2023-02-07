package com.br.projects.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjetoDto {
    private Long id;    
    private String nome;
    private String descricao;
    private Instant dataInicio;
    private Instant dataFim;
    private Instant dataPrevisaoFim;
    private GerenteDto gerente;
    private String statusProjeto;
    private String riscoProjeto;
    private BigDecimal orcamento;

}
