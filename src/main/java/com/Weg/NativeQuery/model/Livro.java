package com.Weg.NativeQuery.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String isbn;

    private BigDecimal preco;

    private LocalDate datapublicacao = LocalDate.now();

    private String categoria;

    @ManyToOne
    private Editora editora;

    @ManyToMany
    @JoinTable(name = "livro_autores")
    private List<Autor> autores = new ArrayList<>();

    public Livro(String titulo, String isbn, BigDecimal preco,  String categoria,
            Editora editora, List<Autor> autores) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.preco = preco;
        this.categoria = categoria;
        this.editora = editora;
        this.autores = autores;
    }
}


