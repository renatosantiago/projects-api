package com.br.projects.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDto {
    private Long id;
    private String nome;
    private Instant dataNascimento;
    private String cpf;
    private boolean funcionario;
}
