package com.satc.todolist.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CategoriaNotaCadastroDTO(
    @NotNull(message = "A descrição da categoria de nota não pode ser nula") @Size(min = 1, max = 50, message = "A descrição da categoria deve ter entre {min} e {max} caracteres") String descricao) {
}