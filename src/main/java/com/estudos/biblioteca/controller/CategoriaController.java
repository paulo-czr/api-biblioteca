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

import com.estudos.biblioteca.dto.CategoriaRequestDto;
import com.estudos.biblioteca.dto.CategoriaResponseDto;
import com.estudos.biblioteca.service.CategoriaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
@Tag(name = "Categorias", description = "Endpoints para gerenciamento de categorias de livros")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service){
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Cadastrar uma nova categoria", description = "Cria uma nova categoria no sistema para classificação de livros.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    })
    public ResponseEntity<CategoriaResponseDto> salvarCategoria(@Valid @RequestBody CategoriaRequestDto dto){
        CategoriaResponseDto categoriaCriada = service.salvarCategoria(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaCriada);
    }

    @GetMapping
    @Operation(summary = "Listar categorias", description = "Retorna uma lista de todas as categorias cadastradas.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de categorias retornada com sucesso")
    })
    public ResponseEntity<List<CategoriaResponseDto>> listarCategorias(){
        List<CategoriaResponseDto> categorias;
        categorias = service.listarCategoria();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar categoria por ID", description = "Retorna os detalhes de uma categoria específica através do seu identificador.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoria encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    public ResponseEntity<CategoriaResponseDto> buscarCategoriaPorId(@PathVariable Long id){
        CategoriaResponseDto categoria = service.buscarCategoriaPorId(id);
        return ResponseEntity.ok().body(categoria);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar uma categoria", description = "Atualiza o nome ou informações de uma categoria existente pelo ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    public ResponseEntity<CategoriaResponseDto> atualizarCategoria(@Valid @PathVariable Long id, @RequestBody CategoriaRequestDto dto){
        CategoriaResponseDto categoria = service.atualizarCategoria(id, dto);
        return ResponseEntity.ok().body(categoria);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir uma categoria", description = "Remove uma categoria do sistema. O parâmetro 'confirm' pode ser usado para forçar a exclusão caso haja dependências.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Categoria excluída com sucesso"),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada"),
        @ApiResponse(responseCode = "409", description = "Conflito: Categoria possui livros vinculados")
    })
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id, @RequestParam(defaultValue = "false") boolean confirm){
        service.deletarCategoriaPorId(id, confirm);
        return ResponseEntity.noContent().build();
    }

}
