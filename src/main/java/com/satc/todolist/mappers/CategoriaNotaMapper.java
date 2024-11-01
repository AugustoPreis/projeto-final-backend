package com.satc.todolist.mappers;

import org.springframework.stereotype.Component;

import com.satc.todolist.dtos.CategoriaNotaDetalhesDTO;
import com.satc.todolist.dtos.CategoriaNotaRespostaDTO;
import com.satc.todolist.models.CategoriaNotaModel;

@Component
public class CategoriaNotaMapper {
  public CategoriaNotaRespostaDTO toCategoriaNotaRespostaDTO(CategoriaNotaModel categoriaNotaModel) {
    return new CategoriaNotaRespostaDTO(categoriaNotaModel.getId(), categoriaNotaModel.getDescricao());
  }

  public CategoriaNotaDetalhesDTO toDetalhesDTO(CategoriaNotaModel categoriaNotaModel) {
    return new CategoriaNotaDetalhesDTO(categoriaNotaModel.getDescricao());
  }
}