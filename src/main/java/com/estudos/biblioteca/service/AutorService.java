package com.estudos.biblioteca.service;

import org.springframework.stereotype.Service;

import com.estudos.biblioteca.dto.AutorRequestDto;
import com.estudos.biblioteca.dto.AutorResponseDto;
import com.estudos.biblioteca.mapper.AutorMapper;
import com.estudos.biblioteca.model.Autor;
import com.estudos.biblioteca.repository.AutorRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class AutorService {

    private final AutorRepository repository;
    private final AutorMapper mapper;

    public AutorService(AutorRepository repository, AutorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Salva um novo Autor no Banco de Dados a partir de um DTO de requisição
     * e retorna um DTO de resposta com os dados essenciais.
     * 
     * @param dto DTO contendo os dados necessários para criação do autor
     * @return DTO de resposta com as informações do autor salvo
     */
    @Transactional
    public AutorResponseDto salvarAutor(AutorRequestDto dto) {
        Autor autorEntity = mapper.toEntity(dto);

        if (autorEntity == null) {
            throw new IllegalArgumentException("Erro ao converter os dados do autor - Autor Nulo");
        }

        Autor autorSalvo = repository.save(autorEntity);
        return mapper.toDto(autorSalvo);
    }

    /**
     * Busca Autor de acordo com o identificador do mesmo
     * 
     * @param id ID do Autor a ser buscado no Banco
     * @return Retorna uma resposta contendo somente os dados essenciais
     */
    public AutorResponseDto buscarAutorPorId(Long id) {
        Autor autorEntity = verificaAutorExistentePorId(id);

        return mapper.toDto(autorEntity);
    }

    /**
     * Atualiza o Autor
     * 
     * @param id  O Identificador Único do Autor a ser atualizado
     * @param dto DTO com os dados necessários para atualização do Autor
     * @return DTO de resposta contendo os dados essenciais
     */
    @Transactional
    public AutorResponseDto atualizarAutor(Long id, AutorRequestDto dto) {
        Autor autorExistente = verificaAutorExistentePorId(id);

        autorExistente.setNome(dto.getNome());

        repository.save(autorExistente);
        return mapper.toDto(autorExistente);
    }

    /**
     * Deleta Autor através do seu ID
     * Exige confirmação se o Autor estiver Livros vinculados a ele
     * 
     * @param id      Identificador do Autor a ser deletado
     * @param confirm Confirmação de Deleção
     */
    @Transactional
    public void deletarAutor(Long id, boolean confirm) {
        Autor autor = verificaAutorExistentePorId(id);

        if (!autor.getLivros().isEmpty() && confirm == false) {
            throw new IllegalArgumentException("O Autor " + autor.getNome() + " - ID: " + id + " possui "
                    + autor.getLivros().size()
                    + " livro(s). Para deletar tudo, envie o parâmetro de confirmação. (/autores/id-autor?confirm=true)");
        }

        repository.deleteById(id);
    }

    /**
     * Verifica se o Autor existe no Banco de Dados
     * 
     * @param id Identificador do Autor
     * @return Se o Autor existir, retorna ele
     */
    public Autor verificaAutorExistentePorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Erro ao converter o ID - ID Nulo");
        }

        Autor autorExistente = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Autor com o ID " + id + "não encontrado."));
        return autorExistente;
    }

}
