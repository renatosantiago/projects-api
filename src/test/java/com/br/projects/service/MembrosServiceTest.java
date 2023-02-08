package com.br.projects.service;

import com.br.projects.dto.MembroDto;
import com.br.projects.mapper.MembrosMapper;
import com.br.projects.model.Membros;
import com.br.projects.model.MembrosId;
import com.br.projects.repository.MembrosRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class MembrosServiceTest {

    @InjectMocks
    private MembrosService service;
    @Mock
    private MembrosRepository repository;
    @Mock
    private MembrosMapper mapper;

    @Test
    void salvarMembro_Deve_Salvar_Membro_Do_Projeto() {
        MembroDto dto = getMembroDto();
        Membros membros = getMembros();

        Mockito.when(mapper.toEntity(dto)).thenReturn(membros);

        Assertions.assertDoesNotThrow(() -> {
            service.salvarMembro(dto);
        });
        Mockito.verify(repository, Mockito.times(1)).save(membros);

    }

    private MembroDto getMembroDto() {
        MembroDto dto = new MembroDto();
        dto.setIdPessoa(1L);
        dto.setIdProjeto(1L);
        return dto;
    }

    private Membros getMembros() {
        Membros membros = new Membros();
        membros.setId(getMembrosId());
        return membros;
    }

    private MembrosId getMembrosId() {
        MembrosId membrosId = new MembrosId();
        membrosId.setIdPessoa(1L);
        membrosId.setIdProjeto(1L);
        return membrosId;
    }
}
