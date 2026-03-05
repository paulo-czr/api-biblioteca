package com.estudos.biblioteca.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.estudos.biblioteca.dto.CategoriaRequestDto;
import com.estudos.biblioteca.dto.CategoriaResponseDto;
import com.estudos.biblioteca.mapper.CategoriaMapper;
import com.estudos.biblioteca.model.Categoria;
import com.estudos.biblioteca.repository.CategoriaRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;
    private final CategoriaMapper mapper;
    
    public CategoriaService(CategoriaRepository repository, CategoriaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Salva uma nova Categoria no Banco de Dados a partir de um DTO de requisição
     * e retorna um DTO de resposta com os dados essenciais.
     * 
     * @param dto DTO contendo os dados necessários para criação do autor
     * @return DTO de resposta com as informações do autor salvo
     */
    @Transactional
    public CategoriaResponseDto salvarCategoria(CategoriaRequestDto dto){
        Categoria categoria = mapper.toEntity(dto);
        Categoria categoriaSalva = repository.save(categoria);

        return mapper.toDto(categoriaSalva);
    }

    /**
     * Lista todas as categorias do Banco de Dados
     * @return Lista com todas as categorias
     */
    public List<CategoriaResponseDto> listarCategoria(){
        List<Categoria> categorias = repository.findAll();
        return mapper.toList(categorias);
    }

    /**
     * Busca uma Categoria de acordo com seu ID específico no Banco de Dados
     * @param id O Identificador da Categoria no Banco de Dados
     * @return DTO com a informações essenciais
     */
    public CategoriaResponseDto buscarCategoriaPorId(Long id){
        Categoria categoria = verificarCategoriaPorId(id);
        return mapper.toDto(categoria);
    }

        /**
     * Atualiza a Categoria
     * 
     * @param id  O Identificador Único da Categoria a ser atualizado
     * @param dto DTO com os dados necessários para atualização da Categoria
     * @return DTO de resposta contendo os dados essenciais
     */
    @Transactional
    public CategoriaResponseDto atualizarCategoria(Long id, CategoriaRequestDto dto){
        Categoria categoriaExistente = verificarCategoriaPorId(id);

        categoriaExistente.setNomeCategoria(dto.getNomeCategoria());

        repository.save(categoriaExistente);
        return mapper.toDto(categoriaExistente);
    }

    /**
     * Deleta Categoria através do seu ID
     * Exige confirmação se a Categoria estiver Livros vinculados a ela
     * 
     * @param id Identificador da Categoria a ser deletada
     * @param confirm Confirmação de Deleção
     */
    public void deletarCategoriaPorId(Long id, boolean confirm){
        Categoria categoria = verificarCategoriaPorId(id);

        if (!categoria.getLivros().isEmpty() && confirm == false) {
            throw new IllegalArgumentException("A Categoria " +categoria.getNomeCategoria()+" - ID: "+id +" possui " +categoria.getLivros().size() +" livro(s). Para deletar tudo, envie o parâmetro de confirmação. (/categorias/id-categoria?confirm=true)");
        }

        repository.deleteById(id);
    }

    /**
     * Verifica se uma Categoria de determinado ID existe no Banco de Dados
     * @param id ID da Categoria para fazer essa verificação
     * @return Se existir, retornará a Categoria Existente, se não um Erro
     */
    public Categoria verificarCategoriaPorId(Long id){
        Categoria categoriaExistente = repository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Categoria com o ID "+id +" não encontrada.")
        );

        return categoriaExistente;
    }
}
