package com.estudos.biblioteca.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.estudos.biblioteca.dto.LivroRequestDto;
import com.estudos.biblioteca.dto.LivroResponseDto;
import com.estudos.biblioteca.mapper.LivroMapper;
import com.estudos.biblioteca.model.Livro;
import com.estudos.biblioteca.model.Autor;
import com.estudos.biblioteca.repository.LivroRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class LivroService {

    private final AutorService autorService;
    private final LivroRepository repository;
    private final LivroMapper mapper;

    public LivroService(LivroRepository repository, LivroMapper mapper, AutorService autorService) {
        this.repository = repository;
        this.mapper = mapper;
        this.autorService = autorService;
    }

    /**
     * Salva um novo Livro no Banco de Dados a partir de um DTO de requisição
     * e retorna um DTO de resposta com os dados essenciais.
     * 
     * @param dto DTO contendo os dados necessários para criação do livro
     * @return DTO de resposta com as informações do livro salvo
     */
    @Transactional
    public LivroResponseDto salvarLivro(LivroRequestDto dto) {
        
        Livro livroEntity = mapper.toEntity(dto);
        //Garante que o Autor existe para vincular o Livro a ele
        Autor autor = autorService.verificaAutorExistentePorId(dto.getAutorId());
        autor.adicionarLivro(livroEntity);

        Livro livroSalvo = repository.save(livroEntity);
        return mapper.toDto(livroSalvo);
    }

    /**
     * Retorna um lista contendo todos os livros do Banco
     * @return Lista de DTO contendo somente os dados essenciais
     */
    public List <LivroResponseDto> buscarTodosLivros() {
        List <Livro> livros = repository.findAll();
        return mapper.toDtoList(livros);
    }

    /**
     * Busca um livro no Banco de Dados pelo ID
     * 
     * @param id ID do livro no Banco de Dados
     * @return DTO de resposta com as informações essenciais do Livro
     */
    public LivroResponseDto buscarLivroPorId(Long id) {
        Livro livro = verificaLivroExistentePorId(id);

        return mapper.toDto(livro);
    }

    /**
     * Busca o Livro pelo Titulo
     * 
     * @param titulo O Titulo do Livro que deseja ser encontrado
     * @return lista de DTOs
     */
    public List<LivroResponseDto> buscarLivroPorTitulo(String titulo) {
        List<Livro> livros = repository.findByTituloContaining(titulo);
        return mapper.toDtoList(livros);
    }

    /**
     * Busca Livro(s) pelo ID do Autor
     * 
     * @param id ID do Autor dos Livros
     * @return Lista de Livros do Autor correspondente
     */
    public List<LivroResponseDto> buscarLivroPorAutorId(Long id) {
        List<Livro> livros = repository.findByAutorId(id);
        return mapper.toDtoList(livros);
    }

    /**
     * Busca Livro(s) de acordo com o nome do Autor
     * 
     * @param nome Nome do Autor do(s) Livro(s)
     * @return Lista de Livros do respectivo Autor
     */
    public List<LivroResponseDto> buscarLivroPorNomeAutor(String nome) {
        List<Livro> livros = repository.findByAutorNomeContaining(nome);
        return mapper.toDtoList(livros);
    }

    /**
     * Atualiza o Livro
     * 
     * @param id  ID do Livro a ser atualizado
     * @param dto DTO contendo os dados necessários para atualização do livro
     * @return DTO de resposta somente com os dados essenciais
     */
    public LivroResponseDto atualizarLivro(Long id, LivroRequestDto dto) {
        Livro livroExistente = verificaLivroExistentePorId(id);

        livroExistente.setTitulo(dto.getTitulo());
        if (!livroExistente.getAutor().getId().equals(dto.getAutorId())) {
            Autor novoAutor = autorService.verificaAutorExistentePorId(dto.getAutorId());

            livroExistente.setAutor(novoAutor);
        }

        Livro livroSalvo = repository.save(livroExistente);
        return mapper.toDto(livroSalvo);
    }

    /**
     * Deleta o Livro através do ID
     * 
     * @param id ID do Livro a ser deletado
     */
    @Transactional
    public void deletarLivroPorId(Long id) {
        verificaLivroExistentePorId(id);

        repository.deleteById(id);
    }

    /**
     * Verifica se o Livro existe no Banco de Dados
     * 
     * @param id Identificador do Livro
     * @return Se o Livro existir, retorna ele
     */
    public Livro verificaLivroExistentePorId(Long id) {
        Livro livroExistente = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Livro com o ID " + id + "não encontrado."));

        return livroExistente;
    }

}
