package com.br.projects.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "pessoa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Instant dataNascimento;
    private String cpf;
    private boolean funcionario;

    @OneToMany(mappedBy = "pessoa")
    private List<Membros> projetos;
}
