package com.Weg.NativeQuery.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Weg.NativeQuery.dto.AutorRequest;
import com.Weg.NativeQuery.dto.AutorResponse;
import com.Weg.NativeQuery.mapper.AutorMapper;
import com.Weg.NativeQuery.services.AutorService;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/autores")
@AllArgsConstructor
public class AutorController {

    private AutorService autorService;

    @PostMapping()
    public ResponseEntity<AutorResponse> create(@RequestBody AutorRequest entity) {
        return new ResponseEntity<>(autorService.create(entity), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<AutorResponse>> readAll() {
        return new ResponseEntity<>(autorService.readAll(), HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AutorResponse> readById(Long id){
        return new ResponseEntity<>(autorService.readById(id), HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorResponse> update(@PathVariable Long id, @RequestBody AutorRequest entity) {
        return new ResponseEntity<>(autorService.update(id, entity), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        autorService.delete(id);
        return ResponseEntity.ok().build();
    }


    

}
