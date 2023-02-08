package com.br.projects.repository;

import com.br.projects.dto.ProjetoMembroDto;
import com.br.projects.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    @Query(value = "SELECT p.id, p.nome FROM projeto p", nativeQuery = true)
    List<ProjetoMembroDto> listarProjetos();
}
