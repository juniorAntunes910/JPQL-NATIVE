package com.Weg.NativeQuery.controllers;

import java.net.http.HttpClient;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Weg.NativeQuery.dto.EditoraRequest;
import com.Weg.NativeQuery.dto.EditoraResponse;
import com.Weg.NativeQuery.services.EditoraService;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/editoras")
@AllArgsConstructor
public class EditoraController {

    private EditoraService editoraService;

    @PostMapping()
    public ResponseEntity<EditoraResponse> create(EditoraRequest editoraRequest) {
        return new ResponseEntity<>(editoraService.create(editoraRequest), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<EditoraResponse>> readAll() {
        return new ResponseEntity<>(editoraService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EditoraResponse> readById(@PathParam Long id) {
        return new ResponseEntity<>(editoraService.readById(id), HttpStatus.ACCEPTED);
    }

}
