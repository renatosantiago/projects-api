package com.br.projects.mapper;

import com.br.projects.dto.PessoaDto;
import com.br.projects.model.Pessoa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PessoaMapper {
    @Mapping(target = "projetos", ignore = true)
    Pessoa toEntity(PessoaDto dto);
}
