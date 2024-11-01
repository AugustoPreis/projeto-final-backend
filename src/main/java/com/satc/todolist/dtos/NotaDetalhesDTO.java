package com.satc.todolist.dtos;

public record NotaDetalhesDTO(UsuarioDetalhesDTO usuarioDetalhes, CategoriaNotaDetalhesDTO categoriaNota, String titulo,
    String descricao) {
}