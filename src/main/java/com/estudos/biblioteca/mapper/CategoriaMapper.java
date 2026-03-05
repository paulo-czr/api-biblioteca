package com.estudos.biblioteca.mapper;


import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.estudos.biblioteca.dto.CategoriaRequestDto;
import com.estudos.biblioteca.dto.CategoriaResponseDto;
import com.estudos.biblioteca.model.Categoria;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "livros", ignore = true)
    public Categoria toEntity(CategoriaRequestDto dto);

    public CategoriaResponseDto toDto(Categoria entity);

    public List<CategoriaResponseDto> toList(List<Categoria> categoria);

}
