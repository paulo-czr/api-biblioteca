package com.estudos.biblioteca.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.estudos.biblioteca.dto.LivroRequestDto;
import com.estudos.biblioteca.dto.LivroResponseDto;
import com.estudos.biblioteca.model.Livro;

@Mapper(componentModel = "spring", uses = {AutorMapper.class})
public interface LivroMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "autor", ignore = true)
    public Livro toEntity(LivroRequestDto dto);

    public LivroResponseDto toDto(Livro livro);

    List<LivroResponseDto> toDtoList(List<Livro> livros);
}
