package com.estudos.biblioteca.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.estudos.biblioteca.dto.AutorRequestDto;
import com.estudos.biblioteca.dto.AutorResponseDto;
import com.estudos.biblioteca.model.Autor;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "livros", ignore = true)
    public Autor toEntity(AutorRequestDto dto);

    @Mapping(target = "totalLivros", 
    expression = "java(autor.getLivros() != null ? autor.getLivros().size() : 0)")
    public AutorResponseDto toDto(Autor autor);
}
