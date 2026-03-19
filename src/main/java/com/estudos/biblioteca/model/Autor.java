package com.estudos.biblioteca.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "autores")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Livro> livros = new ArrayList<>();

    /**
     * Garante que o objeto Livro conheça o Autor antes de salvar
     * 
     * @param livro O Livro a ser adicionado na Lista de Livros do Autor
     */
    public void adicionarLivro(Livro livro) {
        this.livros.add(livro);
        livro.setAutor(this);
    }
}
