package com.satc.todolist.dtos;

public record NotaRespostaDTO(Long id, UsuarioRespostaDTO usuario, CategoriaNotaRespostaDTO categoria, String titulo) {
}