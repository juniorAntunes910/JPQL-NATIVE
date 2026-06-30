package com.Weg.NativeQuery.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record LivroResponse(
        Long id,
        String titulo,
        String isbm,
        BigDecimal preco,
        String categoria,
        EditoraResponse editoraResponse,
        List<AutorResponse> autores
    ) {

}
