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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDto> salvarCategoria(@Valid @RequestBody CategoriaRequestDto dto){
        CategoriaResponseDto categoriaCriada = service.salvarCategoria(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaCriada);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDto>> listarCategorias(){
        List<CategoriaResponseDto> categorias;
        categorias = service.listarCategoria();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> buscarCategoriaPorId(@PathVariable Long id){
        CategoriaResponseDto categoria = service.buscarCategoriaPorId(id);
        return ResponseEntity.ok().body(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> atualizarCategoria(@Valid @PathVariable Long id, @RequestBody CategoriaRequestDto dto){
        CategoriaResponseDto categoria = service.atualizarCategoria(id, dto);
        return ResponseEntity.ok().body(categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id, @RequestParam(defaultValue = "false") boolean confirm){
        service.deletarCategoriaPorId(id, confirm);
        return ResponseEntity.noContent().build();
    }

}
