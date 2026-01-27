package com.estudos.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudos.biblioteca.model.Autor;

public interface AutorRepository extends JpaRepository <Autor, Long> {

}
