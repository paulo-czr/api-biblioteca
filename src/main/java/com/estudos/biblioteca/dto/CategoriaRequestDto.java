package com.estudos.biblioteca.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Schema(name = "CategoriaRequest", description = "Objeto de entrada para criação ou atualização de uma categoria")
public class CategoriaRequestDto {

    @Schema(
        description = "Nome da categoria para classificação dos livros", 
        example = "Tecnologia", 
        maxLength = 50, 
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank
    @Size(max = 50, message = "O nome da categoria é muito grande.")
    private String nomeCategoria;

}
