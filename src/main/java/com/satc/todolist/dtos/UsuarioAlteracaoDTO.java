package com.satc.todolist.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioAlteracaoDTO(
    @NotNull(message = "O nome do usuário não pode ser nulo") String nome,
    @NotNull(message = "O email do usuário não pode ser nulo") @Email(message = "O email do usuário deve ser válido") String email,
    @Size(min = 8, max = 20, message = "A senha deve ter entre 8 e 20 caracteres") String senha) {
}