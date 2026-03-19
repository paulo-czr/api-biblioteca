package com.estudos.biblioteca.controller;

import java.util.List;

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

import com.estudos.biblioteca.dto.LivroRequestDto;
import com.estudos.biblioteca.dto.LivroResponseDto;
import com.estudos.biblioteca.service.LivroService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/livros")
@Tag(name = "Livros", description = "Endpoints para gerenciamento de livros")
public class LivroController {

    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Cadastrar um novo livro", description = "Cria um livro vinculado a um autor e categoria existentes.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Livro criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
        @ApiResponse(responseCode = "404", description = "Autor ou Categoria não encontrados")
    })
    public ResponseEntity<LivroResponseDto> salvarLivro(@Valid @RequestBody LivroRequestDto dto) {
        LivroResponseDto livroCriado = service.salvarLivro(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroCriado);
    }

    @GetMapping
    @Operation(summary = "Listar livros", description = "Retorna uma lista de livros, podendo filtrar pelo título.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de livros retornada com sucesso")
    })
    public ResponseEntity<List<LivroResponseDto>> listarLivros(@RequestParam(required = false) String titulo) {
        List<LivroResponseDto> livros;  

        if (titulo != null) {
            livros = service.buscarLivroPorTitulo(titulo);
        } else {
            livros = service.buscarTodosLivros();
        }

        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar livro por ID", description = "Retorna os detalhes de um livro específico através do seu identificador.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Livro encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })
    public ResponseEntity<LivroResponseDto> buscarLivroPorId(@PathVariable Long id) {
        LivroResponseDto livroCriado = service.buscarLivroPorId(id);
        return ResponseEntity.ok(livroCriado);
    }

    @GetMapping("/autor/{id}")
    @Operation(summary = "Buscar livros por Autor", description = "Retorna todos os livros vinculados a um autor específico.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de livros do autor retornada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Autor não encontrado")
    })
    public ResponseEntity<List<LivroResponseDto>> buscarLivroPorAutorId(@PathVariable Long id) {
        List<LivroResponseDto> livros = service.buscarLivroPorAutorId(id);
        return ResponseEntity.ok(livros);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um livro", description = "Atualiza as informações de um livro existente pelo ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Livro atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
        @ApiResponse(responseCode = "404", description = "Livro, Autor ou Categoria não encontrados")
    })
    public ResponseEntity<LivroResponseDto> atualizarLivro(@PathVariable Long id, @Valid @RequestBody LivroRequestDto dto){
        LivroResponseDto livro = service.atualizarLivro(id, dto);
        return ResponseEntity.ok().body(livro);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um livro", description = "Remove um livro do sistema pelo seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Livro excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id){
        service.deletarLivroPorId(id);
        return ResponseEntity.noContent().build();
    }
}
