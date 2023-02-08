package com.br.projects.controller;

import com.br.projects.dto.ProjetoDto;
import com.br.projects.service.ProjetoService;
import com.br.projects.service.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(ProjetoController.class)
public class ProjetoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private ProjetoService service;
    private Long idNaoExistente;
    private Long idProjeto;

    @BeforeEach
    void setUp() throws Exception {
        idNaoExistente = 2L;
        idProjeto = 1L;

        Mockito.when(service.listarProjetos()).thenReturn(getListDto());
    }

    @Test
    void salvarProjeto_Deve_Salvar_Projeto() throws Exception {
        String jsonBody = mapper.writeValueAsString(getCriarDto());
        ResultActions result =
                mockMvc.perform(post("/projeto")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isCreated());
    }

    @Test
    void atualizarProjeto_Deve_Lancar_BadRequestException_Com_Id_Diferentes() throws Exception {
        String jsonBody = mapper.writeValueAsString(getDto(1L));
        ResultActions result =
                mockMvc.perform(put("/projeto/{id}", idNaoExistente)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isBadRequest());
    }

    @Test
    void atualizarProjeto_Deve_Atualizar_Projeto() throws Exception {
        String jsonBody = mapper.writeValueAsString(getDto(1L));
        ResultActions result =
                mockMvc.perform(put("/projeto/{id}", idProjeto)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }

    @Test
    void excluirProjeto_Deve_Lancar_Exececao_ResourceNotFoundException() throws Exception {
        Mockito.doThrow(ResourceNotFoundException.class).when(service).excluirProjeto(idNaoExistente);
        ResultActions result =
                mockMvc.perform(delete("/projeto/{id}", idNaoExistente)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isNotFound());
    }

    @Test
    void listarProjetos_Deve_Listar_Projetos() throws Exception {
        ResultActions result =
                mockMvc.perform(get("/projeto/listar")
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$[0].id").exists());
        result.andExpect(jsonPath("$[0].nome").exists());
        result.andExpect(jsonPath("$[0].descricao").exists());
        result.andExpect(jsonPath("$[0].statusProjeto").exists());
        result.andExpect(jsonPath("$[0].riscoProjeto").exists());
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

    private ProjetoDto getDto(Long id) {
        ProjetoDto dto = new ProjetoDto();
        dto.setId(id);
        dto.setNome("Nome Projeto");
        dto.setDataInicio(Instant.now());
        dto.setDataPrevisaoFim(Instant.now());
        dto.setStatusProjeto("Em Análise");
        dto.setRiscoProjeto("Médio");
        dto.setDescricao("Descricao Projeto");
        return dto;
    }

    private List<ProjetoDto> getListDto() {
        ArrayList<ProjetoDto> list = new ArrayList<>();
        list.add(getDto(1L));
        list.add(getDto(2L));
        return list;
    }

}
