package com.estudos.biblioteca.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutorRequestDto {
    
    @NotBlank
    @Size(max = 255, message = "O nome do autor é muito grande.")
    private String nome;

}
