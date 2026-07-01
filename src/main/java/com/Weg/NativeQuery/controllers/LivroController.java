package com.Weg.NativeQuery.controllers;

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
import com.Weg.NativeQuery.services.LivroService;

import lombok.AllArgsConstructor;

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

}
