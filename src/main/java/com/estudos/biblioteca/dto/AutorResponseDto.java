package com.estudos.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AutorResponseDto {

    private Long id;
    private String nome;
    private int totalLivros;

}
