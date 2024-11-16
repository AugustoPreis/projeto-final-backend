package com.satc.todolist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.satc.todolist.dtos.NotaAlteracaoDTO;
import com.satc.todolist.dtos.NotaCadastroDTO;
import com.satc.todolist.dtos.NotaDetalhesDTO;
import com.satc.todolist.dtos.NotaRespostaDTO;
import com.satc.todolist.mappers.NotaMapper;
import com.satc.todolist.models.CategoriaNotaModel;
import com.satc.todolist.models.NotaModel;
import com.satc.todolist.models.UsuarioModel;
import com.satc.todolist.repositories.CategoriaNotaRepository;
import com.satc.todolist.repositories.NotaRepository;
import com.satc.todolist.repositories.UsuarioRepository;
import com.satc.todolist.utils.RequestError;

@Service
public class NotaService {
  @Autowired
  NotaMapper notaMapper;

  @Autowired
  NotaRepository notaRepository;

  @Autowired
  CategoriaNotaRepository categoriaNotaRepository;

  @Autowired
  UsuarioRepository usuarioRepository;

  public Page<NotaRespostaDTO> listaNotas(Long usuarioId,Pageable pageable) {
    Page<NotaModel> notasModel = notaRepository.findAllByUsuarioId(usuarioId,pageable);

    return notasModel.map(notaMapper::toNotaRespostaDTO);
  }

  public NotaDetalhesDTO buscaNota(Long usuarioId, Long id) {
    if (id == null) {
      throw new RequestError(HttpStatus.BAD_REQUEST, "O ID da Nota é obrigatório");
    }

    NotaModel notaModel = notaRepository.findOneByIdAndUsuarioId(id, usuarioId);

    if (notaModel == null) {
      throw new RequestError(HttpStatus.NOT_FOUND, "Nota não encontrada");
    }

    return notaMapper.toDetalhesDTO(notaModel);
  }

  public NotaRespostaDTO criaNota(Long usuarioId, NotaCadastroDTO notaCadastroDTO) {
    final UsuarioModel usuarioModel = this.buscaUsuario(usuarioId);
    final CategoriaNotaModel categoriaNotaModel = this.buscaCategoriaNota(notaCadastroDTO.categoriaNotaId());

    final NotaModel notaModel = new NotaModel();

    notaModel.setUsuario(usuarioModel);
    notaModel.setCategoriaNota(categoriaNotaModel);
    notaModel.setTitulo(notaCadastroDTO.titulo());
    notaModel.setDescricao(notaCadastroDTO.descricao());

    NotaModel notaModelSalva = notaRepository.save(notaModel);

    return notaMapper.toNotaRespostaDTO(notaModelSalva);
  }

  public NotaRespostaDTO alteraNota(Long usuarioId, Long id, NotaAlteracaoDTO notaAlteracaoDTO) {
    if (id == null) {
      throw new RequestError(HttpStatus.BAD_REQUEST, "O ID da Nota é obrigatório");
    }

    NotaModel notaModel = notaRepository.findOneByIdAndUsuarioId(id, usuarioId);

    if (notaModel == null) {
      throw new RequestError(HttpStatus.NOT_FOUND, "Nota não encontrada");
    }

    notaModel.setTitulo(notaAlteracaoDTO.titulo());
    notaModel.setDescricao(notaAlteracaoDTO.descricao());

    NotaModel notaModelSalva = notaRepository.save(notaModel);

    return notaMapper.toNotaRespostaDTO(notaModelSalva);
  }

  public NotaDetalhesDTO deletaNota(Long usuarioId, Long id) {
    NotaDetalhesDTO notaRespostaDTO = buscaNota(usuarioId, id);

    notaRepository.deleteById(id);

    return notaRespostaDTO;
  }

  private CategoriaNotaModel buscaCategoriaNota(Long categoriaNotaId) {
    if (categoriaNotaId == null) {
      throw new RequestError(HttpStatus.BAD_REQUEST, "O ID da categoria de Nota é obrigatório");
    }

    CategoriaNotaModel categoriaNotaModel = categoriaNotaRepository.findOneById(categoriaNotaId);

    if (categoriaNotaModel == null) {
      throw new RequestError(HttpStatus.NOT_FOUND, "Categoria de Nota não encontrado");
    }

    return categoriaNotaModel;
  }

  private UsuarioModel buscaUsuario(Long usuarioId) {
    if (usuarioId == null) {
      throw new RequestError(HttpStatus.BAD_REQUEST, "O ID do usuário é obrigatório");
    }

    UsuarioModel usuarioModel = usuarioRepository.findOneById(usuarioId);

    if (usuarioModel == null) {
      throw new RequestError(HttpStatus.NOT_FOUND, "Usuário não encontrado");
    }

    return usuarioModel;
  }
}