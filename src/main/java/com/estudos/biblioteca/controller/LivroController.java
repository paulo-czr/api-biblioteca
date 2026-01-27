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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LivroResponseDto> salvarLivro(@Valid @RequestBody LivroRequestDto dto) {
        LivroResponseDto livroCriado = service.salvarLivro(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroCriado);
    }

    @GetMapping
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
    public ResponseEntity<LivroResponseDto> buscarLivroPorId(@PathVariable Long id) {
        LivroResponseDto livroCriado = service.buscarLivroPorId(id);
        return ResponseEntity.ok(livroCriado);
    }

    @GetMapping("/autor/{id}")
    public ResponseEntity<List<LivroResponseDto>> buscarLivroPorAutorId(@PathVariable Long id) {
        List<LivroResponseDto> livros = service.buscarLivroPorAutorId(id);
        return ResponseEntity.ok(livros);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponseDto> atualizarLivro(@PathVariable Long id, @Valid @RequestBody LivroRequestDto dto){
        LivroResponseDto livro = service.atualizarLivro(id, dto);
        return ResponseEntity.ok().body(livro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivroPorId(@PathVariable Long id){
        service.deletarLivroPorId(id);
        return ResponseEntity.noContent().build();
    }
}
