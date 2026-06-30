package com.Weg.NativeQuery.dto;

import java.math.BigDecimal;
import java.util.List;

public record LivroRequest(
    String titulo,
    String isbm,
    BigDecimal preco,
    String categoria,
    Long editoraId,
    List<Long> autoresId
) {

}
