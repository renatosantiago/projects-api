package com.br.projects.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "membros")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Membros {

    @EmbeddedId
    private MembrosId id;

    @MapsId("idProjeto")
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_projeto", insertable = false, updatable = false)
    private Projeto projeto;

    @ManyToOne
    @JoinColumn(name = "id_pessoa", insertable = false, updatable = false)
    private Pessoa pessoa;
}
