package com.Weg.NativeQuery.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.Weg.NativeQuery.dto.AutorResponse;
import com.Weg.NativeQuery.dto.LivroRequest;
import com.Weg.NativeQuery.dto.LivroResponse;
import com.Weg.NativeQuery.mapper.LivroMapper;
import com.Weg.NativeQuery.model.Autor;
import com.Weg.NativeQuery.model.Editora;
import com.Weg.NativeQuery.model.Livro;
import com.Weg.NativeQuery.projection.AutorNomeNacionalidade;
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

    public List<LivroResponse> readByTitulo(String nome) {
        List<Livro> livros = livroRepository.findByTitulo(nome);
        return livros.stream().map(livroMapper::toResponse).toList();
    }

    public List<LivroResponse> readByCategoriaBeforePreco(String categoria, BigDecimal preco) {
        List<Livro> livros = livroRepository.findByCategoriaAndPrecoBefore(categoria, preco);
        return livros.stream().map(livroMapper::toResponse).toList();
    }

    public List<LivroResponse> readByPrecoBetween(BigDecimal min, BigDecimal max) {
        List<Livro> livros = livroRepository.findByPrecoBetween(min, max);
        return livros.stream().map(livroMapper::toResponse).toList();
    }

    public List<LivroResponse> readByCategoriaIn(List<String> categorias) {
        List<Livro> livros = livroRepository.findByCategoriaIn(categorias);
        return livros.stream().map(livroMapper::toResponse).toList();
    }

    public List<LivroResponse> readByIsbnNull() {
        List<Livro> livros = livroRepository.findByIsbnIsNull();
        return livros.stream().map(livroMapper::toResponse).toList();
    }

    public List<LivroResponse> readByEditoraOrderByTituloAsc(Long id) {
        Editora editora = editoraRepository.findById(id).orElse(null);
        if (editora == null) {
            throw new RuntimeException("A editora não existe");
        }
        List<Livro> livros = livroRepository.findByEditoraOrderByTituloAsc(editora);
        return livros.stream().map(livroMapper::toResponse).toList();
    }

    public Long countByautoresNacionalidade(String nacionalidade) {
        Long count = livroRepository.countByAutoresNacionalidade(nacionalidade);
        return count;
    }

    // JPQL
    public List<String> apenasTitulosPorCategoria(String categoria) {
        List<String> livros = livroRepository.apenasTitulosPorCategoria(categoria);
        return livros;
    }

    public List<LivroResponse> nomeAutorIgualParametro(String nomeAutor) {
        List<Livro> livros = livroRepository.nomeAutorIgualParametro(nomeAutor);
        return livros.stream().map(livroMapper::toResponse).toList();
    }

    public List<LivroResponse> buscarComFetch(Long livroId) {
        List<Livro> livros = livroRepository.buscarComFetch(livroId);
        return livros.stream().map(livroMapper::toResponse).toList();
    }

    public BigDecimal mediaPrecoLivrosDeEditora(Long editoraId) {
        Editora editora = editoraRepository.findById(editoraId).orElse(null);
        if (editora == null) {
            throw new RuntimeException("A editora não existe!");
        }
        BigDecimal media = livroRepository.mediaPrecoLivrosDeEditora(editora);
        return media;
    }

    public List<LivroResponse> livrosPrecoMaiorMedia() {
        List<Livro> livros = livroRepository.livrosPrecoMaiorMedia();
        return livros.stream().map(livroMapper::toResponse).toList();
    }

    public List<LivroResponse> livroDataPublicacao2023() {
        List<Livro> livros = livroRepository.livrosDataPublicacao2023();
        return livros.stream().map(livroMapper::toResponse).toList();
    }

    public List<LivroResponse> buscarPorCategoriaIgnorando(String categoria) {
        List<Livro> livros = livroRepository.buscarPorCategoriaIgnorando(categoria);
        return livros.stream().map(livroMapper::toResponse).toList();
    }

    public List<AutorNomeNacionalidade> retornaAutorMinimo() {
        List<AutorNomeNacionalidade> livros = livroRepository.retornaAutorMinimo();
        return livros;
    }

}
