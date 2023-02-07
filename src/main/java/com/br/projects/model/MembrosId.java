package com.br.projects.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class MembrosId {

    @Column(name = "id_projeto")
    private Long idProjeto;

    @Column(name = "id_pessoa")
    private Long idPessoa;
}
