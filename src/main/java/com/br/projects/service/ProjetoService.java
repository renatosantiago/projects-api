package com.br.projects.service;

import com.br.projects.dto.ProjetoDto;
import com.br.projects.enums.StatusProjeto;
import com.br.projects.mapper.ProjetoMapper;
import com.br.projects.model.Projeto;
import com.br.projects.repository.ProjetoRepository;
import com.br.projects.service.exception.NotAuthorizedActionException;
import com.br.projects.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private ProjetoMapper mapper;

    @Transactional
    public void salvarProjeto(ProjetoDto dto) {
        projetoRepository.save(mapper.toEntity(dto));
    }

    public ProjetoDto bucarPorId(Long id) {
       return mapper.toDto(projetoRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException(MessageFormat.format("id {0} não encontrado", id))));
    }

    public List<ProjetoDto> listarProjetos() {
        return mapper.toListDto(projetoRepository.findAll());
    }

    public ProjetoDto atualizarProjeto(ProjetoDto dto) {
        Projeto projeto = mapper.toEntity(dto);
        return mapper.toDto(projetoRepository.save(projeto));
    }

    public void excluirProjeto(Long id) {
        validateDelete(id);
        projetoRepository.deleteById(id);
    }

    private void validateDelete(Long id) {
        Projeto projeto = projetoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MessageFormat.format("id {0} não encontrado", id)));
        List<String> statusEnum = Arrays.asList(StatusProjeto.INICIADO.getStatus(),
                StatusProjeto.EM_ANDAMENTO.getStatus(), StatusProjeto.ENCERRADO.getStatus());
        statusEnum.forEach(status -> {
            if(projeto.getStatusProjeto().getStatus().equals(status)) {
                throw new NotAuthorizedActionException(
                        MessageFormat.format("Não é permitido excluir o projeto com status {0}", status));
            }
        });
    }
}
