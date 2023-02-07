package com.br.projects.mapper;

import com.br.projects.dto.ProjetoDto;
import com.br.projects.model.Projeto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProjetoMapper {

//    @Mapping(target = "gerente.projetos", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "risco", ignore = true)
    @Mapping(target = "gerente.dataNascimento", ignore = true)
    @Mapping(target = "gerente.cpf", ignore = true)
    @Mapping(target = "gerente.funcionario", ignore = true)
    @Mapping(target = "gerente.projetos", ignore = true)
    @Mapping(target = "integrantes", ignore = true)
    Projeto toEntity(ProjetoDto dto);

//    @Mapping(source = "gerente.id", target = "idPessoa")
    @Mapping(source = "status", target = "statusProjeto")
    @Mapping(source = "risco", target = "riscoProjeto")
    ProjetoDto toDto(Projeto entity);

    List<ProjetoDto> toListDto(List<Projeto> list);

}
