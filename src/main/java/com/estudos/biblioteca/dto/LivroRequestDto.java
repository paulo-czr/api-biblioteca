package com.estudos.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroRequestDto {
    
    @NotBlank
    @Size(max = 255, message = "O titulo é muito grande.")
    private String titulo;

    @NotNull(message = "O ID do Autor que escreveu o Livro é obrigatório.")
    private Long autorId;
    
}
