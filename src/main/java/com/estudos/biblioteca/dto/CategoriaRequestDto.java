package com.estudos.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CategoriaRequestDto {

    @NotBlank
    @Size(max = 50, message = "O nome da categoria é muito grande.")
    private String nomeCategoria;

}
