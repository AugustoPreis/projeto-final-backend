package com.satc.todolist.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.satc.todolist.dtos.NotaDetalhesDTO;
import com.satc.todolist.dtos.NotaRespostaDTO;
import com.satc.todolist.models.NotaModel;

@Component
public class NotaMapper {
  @Autowired
  UsuarioMapper usuarioMapper;

  @Autowired
  CategoriaNotaMapper categoriaNotaMapper;

  public NotaRespostaDTO toNotaRespostaDTO(NotaModel notaModel) {
    return new NotaRespostaDTO(
        notaModel.getId(),
        usuarioMapper.toUsuarioRespostaDTO(notaModel.getUsuario()),
        categoriaNotaMapper.toCategoriaNotaRespostaDTO(notaModel.getCategoriaNota()),
        notaModel.getTitulo());
  }

  public NotaDetalhesDTO toDetalhesDTO(NotaModel notaModel) {
    return new NotaDetalhesDTO(
        usuarioMapper.toDetalhesDTO(notaModel.getUsuario()),
        categoriaNotaMapper.toDetalhesDTO(notaModel.getCategoriaNota()),
        notaModel.getTitulo(),
        notaModel.getDescricao());
  }
}