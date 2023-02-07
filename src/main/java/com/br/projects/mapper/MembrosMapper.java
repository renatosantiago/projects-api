package com.br.projects.mapper;

import com.br.projects.dto.MembroDto;
import com.br.projects.model.Membros;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface MembrosMapper {

    @Mapping(target = "projeto.nome", ignore = true)
    @Mapping(target = "projeto.dataInicio", ignore = true)
    @Mapping(target = "projeto.dataFim", ignore = true)
    @Mapping(target = "projeto.dataPrevisaoFim", ignore = true)
    @Mapping(target = "projeto.descricao", ignore = true)
    @Mapping(target = "projeto.statusProjeto", ignore = true)
    @Mapping(target = "projeto.status", ignore = true)
    @Mapping(target = "projeto.riscoProjeto", ignore = true)
    @Mapping(target = "pessoa.nome", ignore = true)
    @Mapping(target = "pessoa.dataNascimento", ignore = true)
    @Mapping(target = "pessoa.cpf", ignore = true)
    @Mapping(target = "pessoa.funcionario", ignore = true)
    @Mapping(target = "projeto.id", source = "idProjeto")
    @Mapping(target = "pessoa.id", source = "idPessoa")
    @Mapping(target = "id.idProjeto", source = "idProjeto")
    @Mapping(target = "id.idPessoa", source = "idPessoa")
    Membros toEntity(MembroDto dto);
}
