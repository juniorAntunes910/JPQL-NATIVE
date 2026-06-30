package com.Weg.NativeQuery.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.Weg.NativeQuery.dto.LivroRequest;
import com.Weg.NativeQuery.dto.LivroResponse;
import com.Weg.NativeQuery.model.Autor;
import com.Weg.NativeQuery.model.Editora;
import com.Weg.NativeQuery.model.Livro;
import com.Weg.NativeQuery.repository.AutorRepository;
import com.Weg.NativeQuery.repository.EditoraRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class LivroMapper {

    private EditoraRepository editoraRepository;
    private AutorRepository autorRepository;
    private EditoraMapper editoraMapper;
    private AutorMapper autorMapper;

    public Livro toEntity(LivroRequest livroRequest) {
        Editora editora = editoraRepository.findById(livroRequest.editoraId()).orElse(null);
        List<Autor> autores = autorRepository.findAllById(livroRequest.autoresId());
        return new Livro(
                livroRequest.titulo(),
                livroRequest.isbm(),
                livroRequest.preco(),
                livroRequest.categoria(),
                editora,
                autores);

    }

    public LivroResponse toResponse(Livro livro){
        return new LivroResponse(
            livro.getId(),
            livro.getTitulo(),
            livro.getIsbn(),
            livro.getPreco(),
            livro.getCategoria(),
            editoraMapper.toREsponse(livro.getEditora()),
            livro.getAutores().stream().map(autorMapper::toResponse).toList());
    }

}
