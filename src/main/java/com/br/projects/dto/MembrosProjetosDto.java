package com.br.projects.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MembrosProjetosDto {
    private List<ProjetoMembroDto> projetos = new ArrayList<>();
    private List<PessoaMembroDto> membros = new ArrayList<>();
}
