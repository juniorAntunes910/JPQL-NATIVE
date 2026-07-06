package com.Weg.NativeQuery.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Weg.NativeQuery.dto.EditoraRequest;
import com.Weg.NativeQuery.dto.EditoraResponse;
import com.Weg.NativeQuery.dto.LivroRequest;
import com.Weg.NativeQuery.dto.LivroResponse;
import com.Weg.NativeQuery.projection.AutorNomeNacionalidade;
import com.Weg.NativeQuery.services.LivroService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/livros")
@AllArgsConstructor
public class LivroController {

    private final LivroService livroService;

    @PostMapping()
    public ResponseEntity<LivroResponse> create(LivroRequest livroRequest) {
        return new ResponseEntity<>(livroService.create(livroRequest), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<LivroResponse>> readAll() {
        return new ResponseEntity<>(livroService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponse> readById(@PathVariable Long id) {
        return new ResponseEntity<>(livroService.readById(id), HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponse> update(@PathVariable Long id, @RequestBody LivroRequest entity) {
        return new ResponseEntity<>(livroService.update(id, entity), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        livroService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<LivroResponse>> readByTitulo(@RequestParam String nome) {
        return new ResponseEntity<>(livroService.readByTitulo(nome), HttpStatus.ACCEPTED);
    }

    @GetMapping("/categoriaPreco/{categoria}")
    public ResponseEntity<List<LivroResponse>> readByCategoriaBeforePreco(@PathVariable String categoria,
            @RequestBody BigDecimal preco) {
        return new ResponseEntity<>(livroService.readByCategoriaBeforePreco(categoria, preco), HttpStatus.ACCEPTED);
    }

    @GetMapping("/precobetween")
    public ResponseEntity<List<LivroResponse>> readByPrecoBetween(@RequestParam BigDecimal min,
            @RequestParam BigDecimal max) {
        return new ResponseEntity<>(livroService.readByPrecoBetween(min, max), HttpStatus.ACCEPTED);
    }

    @GetMapping("/categoriaIn")
    public ResponseEntity<List<LivroResponse>> readByCategoriaIn(@RequestBody List<String> categorias) {
        return new ResponseEntity<>(livroService.readByCategoriaIn(categorias), HttpStatus.ACCEPTED);
    }

    @GetMapping("/readByIsbnNull")
    public ResponseEntity<List<LivroResponse>> readByIsbnNull() {
        return new ResponseEntity<>(livroService.readByIsbnNull(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/editoraTitulo/{id}")
    public ResponseEntity<List<LivroResponse>> readByEditoraOrderByTituloAsc(@PathVariable long id) {
        return new ResponseEntity<>(livroService.readByEditoraOrderByTituloAsc(id), HttpStatus.ACCEPTED);
    }

    @GetMapping("/countAutoresNacionalidade/{nacionalidade}")
    public ResponseEntity<Long> coutByAutoresNacionalidade(@PathVariable String nacionalidade) {
        return new ResponseEntity<>(livroService.countByautoresNacionalidade(nacionalidade), HttpStatus.ACCEPTED);
    }

    @GetMapping("/titulosPorCategoria/{categoria}")
    public ResponseEntity<List<String>> apenasTitulosPorCategoria(@PathVariable String categoria) {
        return new ResponseEntity<>(livroService.apenasTitulosPorCategoria(categoria), HttpStatus.ACCEPTED);
    }

    @GetMapping("/nomeAutorIgualParametro/{nomeAutor}")
    public ResponseEntity<List<LivroResponse>> nomeAutorIgualParametro(@PathVariable String nomeAutor) {
        return new ResponseEntity<>(livroService.nomeAutorIgualParametro(nomeAutor), HttpStatus.ACCEPTED);
    }

    @GetMapping("/buscarComFetch/{livroId}")
    public ResponseEntity<List<LivroResponse>> buscarComFetch(@PathVariable long livroId) {
        return new ResponseEntity<>(livroService.buscarComFetch(livroId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/mediaLivrosPorEditora/{editoraId}")
    public ResponseEntity<BigDecimal> mediaPrecoLivroEditora(@PathVariable long editoraId) {
        return new ResponseEntity<>(livroService.mediaPrecoLivrosDeEditora(editoraId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/livrosPrecoMaiorMedia")
    public ResponseEntity<List<LivroResponse>> livroPrecoMaiorMedia() {
        return new ResponseEntity<>(livroService.livrosPrecoMaiorMedia(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/livroDatPublicacao2023")
    public ResponseEntity<List<LivroResponse>> livrodataPublicacao2023() {
        return new ResponseEntity<>(livroService.livroDataPublicacao2023(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/categoriaIgnorando/{categoria}")
    public ResponseEntity<List<LivroResponse>> categoriaIgnorando(@PathVariable String categoria) {
        return new ResponseEntity<>(livroService.buscarPorCategoriaIgnorando(categoria), HttpStatus.ACCEPTED);
    }

    @GetMapping("/retornaAutorMinimo")
    public ResponseEntity<List<AutorNomeNacionalidade>> retornaAutorMinimo() {
        return new ResponseEntity<>(livroService.retornaAutorMinimo(), HttpStatus.ACCEPTED);
    }

}
