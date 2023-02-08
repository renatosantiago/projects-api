package com.br.projects.service;

import com.br.projects.dto.PessoaDto;
import com.br.projects.dto.PessoaMembroDto;
import com.br.projects.mapper.PessoaMapper;
import com.br.projects.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaMapper mapper;

    @Transactional
    public void salvarPessoa(PessoaDto dto) {
        pessoaRepository.save(mapper.toEntity(dto));
    }

    @Transactional
    public List<PessoaMembroDto> listarPessoas() {
        return pessoaRepository.listPessoas();
    }

}
