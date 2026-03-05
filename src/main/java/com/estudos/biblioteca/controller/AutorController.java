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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/autores")
public class AutorController {
    
    private final AutorService service;

    public AutorController(AutorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AutorResponseDto> salvarAutor(@Valid @RequestBody AutorRequestDto dto){
        AutorResponseDto autor = service.salvarAutor(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(autor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorResponseDto> buscarAutorPorId(@PathVariable Long id){
        AutorResponseDto autor = service.buscarAutorPorId(id);
        return ResponseEntity.ok().body(autor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorResponseDto> atualizarAutor(@Valid @PathVariable Long id, @RequestBody AutorRequestDto dto){
        AutorResponseDto autor = service.atualizarAutor(id, dto);
        return ResponseEntity.ok().body(autor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAutor(@PathVariable Long id, @RequestParam(defaultValue = "false") boolean confirm){
        service.deletarAutor(id, confirm);
        return ResponseEntity.noContent().build();
    }
}
