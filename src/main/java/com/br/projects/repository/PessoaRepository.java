package com.br.projects.repository;

import com.br.projects.dto.PessoaMembroDto;
import com.br.projects.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    @Query(value = "SELECT p.id as id, p.nome as nome FROM pessoa p WHERE p.funcionario is true", nativeQuery = true)
    List<PessoaMembroDto> listPessoas();
}
