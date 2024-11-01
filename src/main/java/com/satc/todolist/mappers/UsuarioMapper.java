package com.satc.todolist.mappers;

import com.satc.todolist.dtos.UsuarioDetalhesDTO;
import com.satc.todolist.dtos.UsuarioRespostaDTO;
import com.satc.todolist.models.UsuarioModel;

import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
  public UsuarioRespostaDTO toUsuarioRespostaDTO(UsuarioModel usuarioModel) {
    return new UsuarioRespostaDTO(usuarioModel.getId(), usuarioModel.getNome());
  }

  public UsuarioDetalhesDTO toDetalhesDTO(UsuarioModel usuarioModel) {
    return new UsuarioDetalhesDTO(usuarioModel.getNome(), usuarioModel.getEmail());
  }
}