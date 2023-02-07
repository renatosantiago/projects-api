package com.br.projects.model;

import com.br.projects.enums.RiscoProjeto;
import com.br.projects.enums.StatusProjeto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "projeto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    private Instant dataInicio;

    private Instant dataFim;

    private Instant dataPrevisaoFim;

    private BigDecimal orcamento;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusProjeto statusProjeto;

    @Enumerated(EnumType.STRING)
    private RiscoProjeto riscoProjeto;

    @ManyToOne
    @JoinColumn(name = "id_gerente")
    private Pessoa gerente;

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.REMOVE)
    private List<Membros> integrantes;

    @Transient
    private String status;
    @Transient
    private String risco;

    public void setStatusProjeto(String status) {
        this.statusProjeto = StatusProjeto.fromString(status);
    }

    public String getStatus() {
        return getStatusProjeto().getStatus();
    }

    public void setRiscoProjeto(String risco) { this.riscoProjeto = RiscoProjeto.fromString(risco); }

    public String getRisco() { return getRiscoProjeto().getRisco(); }


}
