package com.estudos.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudos.biblioteca.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
