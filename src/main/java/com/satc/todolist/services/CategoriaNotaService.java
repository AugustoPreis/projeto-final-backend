package com.satc.todolist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.satc.todolist.dtos.CategoriaNotaCadastroDTO;
import com.satc.todolist.dtos.CategoriaNotaDetalhesDTO;
import com.satc.todolist.dtos.CategoriaNotaRespostaDTO;
import com.satc.todolist.mappers.CategoriaNotaMapper;
import com.satc.todolist.models.CategoriaNotaModel;
import com.satc.todolist.repositories.CategoriaNotaRepository;
import com.satc.todolist.utils.RequestError;

@Service
public class CategoriaNotaService {
  @Autowired
  CategoriaNotaMapper categoriaNotaMapper;

  @Autowired
  CategoriaNotaRepository categoriaNotaRepository;

  public Page<CategoriaNotaRespostaDTO> listaCategoriasNota(Pageable pageable) {
    Page<CategoriaNotaModel> categoriasNotaModel = categoriaNotaRepository.findAll(pageable);

    return categoriasNotaModel.map(categoriaNotaMapper::toCategoriaNotaRespostaDTO);
  }

  public CategoriaNotaDetalhesDTO buscaCategoriaNota(Long id) {
    if (id == null) {
      throw new RequestError(HttpStatus.BAD_REQUEST, "ID não informado.");
    }

    final CategoriaNotaModel categoriaNotaModel = categoriaNotaRepository.findOneById(id);

    if (categoriaNotaModel == null) {
      throw new RequestError(HttpStatus.NOT_FOUND, "Categoria de Nota não encontrada.");
    }

    return categoriaNotaMapper.toDetalhesDTO(categoriaNotaModel);
  }

  public CategoriaNotaRespostaDTO criaCategoriaNota(CategoriaNotaCadastroDTO categoriaNotaCadastroDTO) {
    final CategoriaNotaModel categoriaNotaModel = new CategoriaNotaModel();

    categoriaNotaModel.setDescricao(categoriaNotaCadastroDTO.descricao());

    final CategoriaNotaModel categoriaNotaModelSalva = categoriaNotaRepository.save(categoriaNotaModel);

    return categoriaNotaMapper.toCategoriaNotaRespostaDTO(categoriaNotaModelSalva);
  }

  public CategoriaNotaRespostaDTO alteraCategoriaNota(Long id, CategoriaNotaCadastroDTO categoriaNotaAlteracaoDTO) {
    if (id == null) {
      throw new RequestError(HttpStatus.BAD_REQUEST, "ID não informado.");
    }

    final CategoriaNotaModel categoriaNotaModel = categoriaNotaRepository.findOneById(id);

    if (categoriaNotaModel == null) {
      throw new RequestError(HttpStatus.NOT_FOUND, "Categoria de Nota não encontrada.");
    }

    categoriaNotaModel.setDescricao(categoriaNotaAlteracaoDTO.descricao());

    final CategoriaNotaModel categoriaNotaModelSalva = categoriaNotaRepository.save(categoriaNotaModel);

    return categoriaNotaMapper.toCategoriaNotaRespostaDTO(categoriaNotaModelSalva);
  }

  public CategoriaNotaDetalhesDTO deletaCategoriaNota(Long id) {
    CategoriaNotaDetalhesDTO categoriaNotaDetalhesDTO = this.buscaCategoriaNota(id);

    categoriaNotaRepository.deleteById(id);

    return categoriaNotaDetalhesDTO;
  }
}
