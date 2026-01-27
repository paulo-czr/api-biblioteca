package com.estudos.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudos.biblioteca.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{
    
    public List<Livro> findByTituloContaining(String titulo);

    public List<Livro> findByAutorId(Long id);
    
    public List<Livro> findByAutorNomeContaining(String nome);    

}
