package com.br.projects.service;

import com.br.projects.dto.ProjetoDto;
import com.br.projects.mapper.ProjetoMapper;
import com.br.projects.model.Projeto;
import com.br.projects.repository.ProjetoRepository;
import com.br.projects.service.exception.NotAuthorizedActionException;
import com.br.projects.service.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class ProjetoServiceTest {

    @InjectMocks
    ProjetoService service;
    @Mock
    ProjetoRepository repository;
    @Mock
    ProjetoMapper mapper;

    private ProjetoDto dto;
    private ProjetoDto dtoEdited;
    private Projeto entity;
    private Projeto savedEntity;
    private Long idNaoExistente;
    private Long idProjeto;

    @BeforeEach()
    void setUp() throws Exception {
        idNaoExistente = 2L;
        idProjeto = 1L;
        dto = getCriarDto();
        entity = getProjeto();
        Mockito.when(mapper.toEntity(dto)).thenReturn(entity);
    }

    @Test
    public void salvarProjeto_Deve_Salvar_Projeto() {
        Assertions.assertDoesNotThrow(() -> {
            service.salvarProjeto(dto);
        });
        Mockito.verify(repository, Mockito.times(1)).save(entity);
    }

    @Test
    public void atualizarProjeto_Deve_Atualizar_Projeto() {
        dtoEdited = getEditedDto();
        savedEntity = getProjetoCriado("Em Andamento");

        Mockito.when(mapper.toEntity(dtoEdited)).thenReturn(savedEntity);
        Mockito.when(repository.save(savedEntity)).thenReturn(savedEntity);
        Mockito.when(mapper.toDto(savedEntity)).thenReturn(dtoEdited);

        ProjetoDto result = service.atualizarProjeto(dtoEdited);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), 1L);
        Mockito.verify(repository, Mockito.times(1)).save(savedEntity);
    }

    @Test
    public void excluirProjeto_Deve_Lancar_Exececao_ResourceNotFoundException() {
        Mockito.doThrow(ResourceNotFoundException.class).when(repository).findById(idNaoExistente);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.excluirProjeto(idNaoExistente);
        });
        Mockito.verify(repository, Mockito.times(1)).findById(idNaoExistente);
    }

    @Test
    public void excluirProjeto_Deve_Lancar_Excecao_NotAuthorizedActionException_Para_Status_Nao_Permitido() {
        savedEntity = getProjetoCriado("Em Andamento");
        Mockito.when(repository.findById(idProjeto)).thenReturn(Optional.of(savedEntity));
        Assertions.assertThrows(NotAuthorizedActionException.class, () -> {
           service.excluirProjeto(idProjeto);
        });
    }

    @Test
    public void excluirProjeto_Deve_Excluir_Projeto() {
        savedEntity = getProjetoCriado("Em Análise");
        Mockito.when(repository.findById(idProjeto)).thenReturn(Optional.of(savedEntity));
        Assertions.assertDoesNotThrow(() -> {
            service.excluirProjeto(idProjeto);
        });
        Mockito.verify(repository, Mockito.times(1)).deleteById(idProjeto);
    }

    private ProjetoDto getCriarDto() {
        ProjetoDto dto = new ProjetoDto();
        dto.setNome("Nome Projeto");
        dto.setDataInicio(Instant.now());
        dto.setDataPrevisaoFim(Instant.now());
        dto.setStatusProjeto("Em Análise");
        dto.setRiscoProjeto("Médio");
        dto.setDescricao("Descricao Projeto");
        return dto;
    }

    private ProjetoDto getEditedDto() {
        ProjetoDto dto = new ProjetoDto();
        dto.setId(1L);
        dto.setNome("Nome Projeto");
        dto.setDataInicio(Instant.now());
        dto.setDataPrevisaoFim(Instant.now());
        dto.setStatusProjeto("Em Análise");
        dto.setRiscoProjeto("Médio");
        dto.setDescricao("Descricao Projeto");
        return dto;
    }

    private Projeto getProjeto() {
        Projeto projeto = new Projeto();
        projeto.setNome("Nome Projeto");
        projeto.setDataInicio(Instant.now());
        projeto.setDataPrevisaoFim(Instant.now());
        projeto.setStatusProjeto("Em Análise");
        projeto.setRiscoProjeto("Médio");
        projeto.setDescricao("Descricao Projeto");
        return projeto;
    }

    private Projeto getProjetoCriado(String status) {
        Projeto projeto = new Projeto();
        projeto.setId(1L);
        projeto.setNome("Nome Projeto");
        projeto.setDataInicio(Instant.now());
        projeto.setDataPrevisaoFim(Instant.now());
        projeto.setStatusProjeto(status);
        projeto.setRiscoProjeto("Médio");
        projeto.setDescricao("Descricao Projeto");
        return projeto;
    }

}
