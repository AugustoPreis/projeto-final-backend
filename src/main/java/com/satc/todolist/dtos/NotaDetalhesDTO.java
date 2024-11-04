package com.satc.todolist.dtos;

public record NotaDetalhesDTO(UsuarioDetalhesDTO usuario, CategoriaNotaDetalhesDTO categoria, String titulo,
    String descricao) {
}