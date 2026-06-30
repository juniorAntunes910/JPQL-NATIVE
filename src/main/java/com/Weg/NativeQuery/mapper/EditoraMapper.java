package com.Weg.NativeQuery.mapper;

import org.springframework.stereotype.Component;

import com.Weg.NativeQuery.dto.EditoraRequest;
import com.Weg.NativeQuery.dto.EditoraResponse;
import com.Weg.NativeQuery.model.Editora;

@Component
public class EditoraMapper {

    public Editora toEntity(EditoraRequest editoraRequest) {
        return new Editora(editoraRequest.nome());
    }

    public EditoraResponse toREsponse(Editora editora) {
        return new EditoraResponse(
                editora.getId(),
                editora.getNome());
    }

}
