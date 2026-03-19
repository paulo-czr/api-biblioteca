package com.estudos.biblioteca.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "LivroRequest", description = "Objeto de entrada para criação ou atualização de um livro")
public class LivroRequestDto {
    
    @Schema(
        description = "Título completo do livro", 
        example = "Código Limpo", 
        maxLength = 255, 
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank
    @Size(max = 255, message = "O titulo é muito grande.")
    private String titulo;

    @Schema(
        description = "ID único do autor que já deve estar cadastrado no sistema", 
        example = "1", 
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "O ID do Autor que escreveu o Livro é obrigatório.")
    private Long autorId;

    @Schema(
        description = "ID único da categoria que já deve estar cadastrada no sistema", 
        example = "1", 
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "O ID da Categoria do Livro é obrigatória.")
    private Long categoriaId;
    
}
