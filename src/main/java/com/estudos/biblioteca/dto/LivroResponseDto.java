package com.estudos.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LivroResponseDto {
    
    private Long id;
    private String titulo;
    private AutorResponseDto autor;
    private CategoriaResponseDto categoria;

}
