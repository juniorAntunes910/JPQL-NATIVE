package com.Weg.NativeQuery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Weg.NativeQuery.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    List<Autor> findByNomeContainingIgnoreCase(String caracteres);
}
