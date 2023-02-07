package com.br.projects.service;

import com.br.projects.dto.MembroDto;
import com.br.projects.dto.MembrosProjetosDto;
import com.br.projects.mapper.MembrosMapper;
import com.br.projects.repository.MembrosRepository;
import com.br.projects.repository.PessoaRepository;
import com.br.projects.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MembrosService {

    @Autowired
    private MembrosRepository membrosRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    ProjetoRepository projetoRepository;
    @Autowired
    MembrosMapper mapper;

    @Transactional
    public void salvarMembro(MembroDto dto) {
        membrosRepository.save(mapper.toEntity(dto));
    }

    @Transactional
    public MembrosProjetosDto listarMembrosProjetos() {
        MembrosProjetosDto dto = new MembrosProjetosDto();
        dto.setMembros(pessoaRepository.listPessoas());
        dto.setProjetos(projetoRepository.listarProjetos());
        return dto;
    }
}
