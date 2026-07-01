package com.Weg.NativeQuery.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Weg.NativeQuery.dto.LivroRequest;
import com.Weg.NativeQuery.dto.LivroResponse;
import com.Weg.NativeQuery.mapper.LivroMapper;
import com.Weg.NativeQuery.model.Autor;
import com.Weg.NativeQuery.model.Editora;
import com.Weg.NativeQuery.model.Livro;
import com.Weg.NativeQuery.repository.AutorRepository;
import com.Weg.NativeQuery.repository.EditoraRepository;
import com.Weg.NativeQuery.repository.LivroRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LivroService {

    private final LivroMapper livroMapper;
    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;
    private final EditoraRepository editoraRepository;

    public LivroResponse create(LivroRequest livroRequest) {
        Livro livro = livroMapper.toEntity(livroRequest);
        livroRepository.save(livro);
        return livroMapper.toResponse(livro);
    }

    public List<LivroResponse> readAll() {
        List<Livro> livros = livroRepository.findAll();
        return livros.stream().map(livroMapper::toResponse).toList();
    }

    public LivroResponse readById(Long id) {
        Livro livro = livroRepository.findById(id).orElse(null);
        if (livro == null) {
            throw new RuntimeException("O Livro não existe");
        }
        return livroMapper.toResponse(livro);
    }

    public LivroResponse update(Long id, LivroRequest livroRequest) {
        Livro livro = livroRepository.findById(id).orElse(null);
        if (livro == null) {
            throw new RuntimeException("O Livro não existe");
        }

        livro.setCategoria(livroRequest.categoria());
        livro.setIsbn(livroRequest.isbm());
        livro.setPreco(livroRequest.preco());
        livro.setTitulo(livroRequest.titulo());
        Editora editora = editoraRepository.findById(id).orElse(null);
        if (editora == null) {
            throw new RuntimeException("Editora não existe");
        }
        List<Autor> autores = autorRepository.findAllById(livroRequest.autoresId());
        livro.setAutores(autores);
        livro.setEditora(editora);
        livroRepository.save(livro);
        return livroMapper.toResponse(livro);
    }

    public void delete(Long id) {
        Livro livro = livroRepository.findById(id).orElse(null);
        if (livro == null) {
            throw new RuntimeException("O Livro não existe");
        }
        livroRepository.delete(livro);
    }

}
