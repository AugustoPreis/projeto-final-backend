package com.satc.todolist.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioAlteracaoDTO(
    @NotNull(message = "O nome do usuário não pode ser nulo") @Size(min = 1, max = 100, message = "O nome do usuário deve ter entre {min} e {max} caracteres") String nome,
    @NotNull(message = "O email do usuário não pode ser nulo") @Email(message = "O email do usuário deve ser válido") @Size(min = 1, max = 100, message = "O email do usuário deve ter entre {min} e {max} caracteres") String email,
    @Size(min = 8, max = 20, message = "A senha deve ter entre {min} e {max} caracteres") String senha) {
}