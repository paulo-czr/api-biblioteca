package com.estudos.biblioteca.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "AutorRequest", description = "Objeto de entrada para criação ou atualização de um autor")
public class AutorRequestDto {
    
    @Schema(
        description = "Nome completo do autor das obras", 
        example = "Robert C. Martin", 
        maxLength = 255, 
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank
    @Size(max = 255, message = "O nome do autor é muito grande.")
    private String nome;

}
