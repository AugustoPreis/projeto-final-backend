package com.satc.todolist.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NotaCadastroDTO(
    @NotNull(message = "A categoria da nota é obrigatória") Long categoriaNotaId,
    @NotNull(message = "O título da nota é obrigatório") @Size(min = 1, max = 100, message = "O título da nota deve ter entre {min} e {max} caracteres") String titulo,
    String descricao) {
}