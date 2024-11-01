package com.satc.todolist.dtos;

import jakarta.validation.constraints.NotNull;

public record CategoriaNotaCadastroDTO(
    @NotNull(message = "A descrição da categoria de nota não pode ser nula") String descricao) {
}