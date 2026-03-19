package com.estudos.biblioteca.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.biblioteca.dto.AutorRequestDto;
import com.estudos.biblioteca.dto.AutorResponseDto;
import com.estudos.biblioteca.service.AutorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/autores")
@Tag(name = "Autores", description = "Endpoints para gerenciamento de autores do acervo")
public class AutorController {
    
    private final AutorService service;

    public AutorController(AutorService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Cadastrar um novo autor", description = "Registra um novo autor no sistema para posterior vínculo com livros.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Autor criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    })
    public ResponseEntity<AutorResponseDto> salvarAutor(@Valid @RequestBody AutorRequestDto dto){
        AutorResponseDto autor = service.salvarAutor(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(autor);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar autor por ID", description = "Retorna as informações detalhadas de um autor específico através do seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Autor encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Autor não encontrado")
    })
    public ResponseEntity<AutorResponseDto> buscarAutorPorId(@PathVariable Long id){
        AutorResponseDto autor = service.buscarAutorPorId(id);
        return ResponseEntity.ok().body(autor);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um autor", description = "Atualiza os dados cadastrais de um autor existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Autor atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
        @ApiResponse(responseCode = "404", description = "Autor não encontrado")
    })
    public ResponseEntity<AutorResponseDto> atualizarAutor(@Valid @PathVariable Long id, @RequestBody AutorRequestDto dto){
        AutorResponseDto autor = service.atualizarAutor(id, dto);
        return ResponseEntity.ok().body(autor);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um autor", description = "Remove um autor do sistema. O uso do parâmetro 'confirm' permite lidar com autores que possuem obras vinculadas.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Autor excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Autor não encontrado"),
        @ApiResponse(responseCode = "409", description = "Conflito: Autor possui livros registrados")
    })
    public ResponseEntity<Void> deletarAutor(@PathVariable Long id, @RequestParam(defaultValue = "false") boolean confirm){
        service.deletarAutor(id, confirm);
        return ResponseEntity.noContent().build();
    }
}
