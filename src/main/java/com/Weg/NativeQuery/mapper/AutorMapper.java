package com.Weg.NativeQuery.mapper;

import org.springframework.stereotype.Component;

import com.Weg.NativeQuery.dto.AutorRequest;
import com.Weg.NativeQuery.dto.AutorResponse;
import com.Weg.NativeQuery.model.Autor;

@Component
public class AutorMapper {

    public Autor toEntity(AutorRequest autorRequest) {
        return new Autor(
                autorRequest.nome(),
                autorRequest.nacionalidade());
    }

    public AutorResponse toResponse(Autor autor) {
        return new AutorResponse(
                autor.getId(),
                autor.getNome(),
                autor.getNacionalidade());
    }

}
