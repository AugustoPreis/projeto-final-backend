package com.satc.todolist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.satc.todolist.dtos.UsuarioDetalhesDTO;
import com.satc.todolist.dtos.UsuarioAlteracaoDTO;
import com.satc.todolist.dtos.UsuarioCadastroDTO;
import com.satc.todolist.dtos.UsuarioRespostaDTO;
import com.satc.todolist.mappers.UsuarioMapper;
import com.satc.todolist.models.UsuarioModel;
import com.satc.todolist.repositories.NotaRepository;
import com.satc.todolist.repositories.UsuarioRepository;
import com.satc.todolist.utils.RequestError;

@Service
public class UsuarioService {
  @Autowired
  UsuarioMapper usuarioMapper;

  @Autowired
  UsuarioRepository usuarioRepository;

  @Autowired
  NotaRepository notaRepository;

  public List<UsuarioRespostaDTO> listaUsuarios() {
    final List<UsuarioModel> usuarioModels = usuarioRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));

    return usuarioModels.stream().map(usuarioMapper::toUsuarioRespostaDTO).toList();
  }

  public UsuarioDetalhesDTO buscaUsuario(Long id) {
    if (id == null) {
      throw new RequestError(HttpStatus.BAD_REQUEST, "ID não informado.");
    }

    final UsuarioModel usuarioModel = usuarioRepository.findOneById(id);

    if (usuarioModel == null) {
      throw new RequestError(HttpStatus.NOT_FOUND, "Usuário não encontrado.");
    }

    return usuarioMapper.toDetalhesDTO(usuarioModel);
  }

  public UsuarioRespostaDTO criaUsuario(UsuarioCadastroDTO usuarioCadastroDTO) {
    final UsuarioModel usuarioModel = new UsuarioModel();

    usuarioModel.setNome(usuarioCadastroDTO.nome());
    usuarioModel.setEmail(usuarioCadastroDTO.email());
    usuarioModel.setSenha(usuarioCadastroDTO.senha());

    this.validaCredenciaisUsuario(usuarioModel.getEmail(), usuarioModel.getId());

    final UsuarioModel usuarioModelSalvo = usuarioRepository.save(usuarioModel);

    return usuarioMapper.toUsuarioRespostaDTO(usuarioModelSalvo);
  }

  public UsuarioRespostaDTO alteraUsuario(Long id, UsuarioAlteracaoDTO usuarioAlteracaoDTO) {
    if (id == null) {
      throw new RequestError(HttpStatus.BAD_REQUEST, "ID não informado.");
    }

    final UsuarioModel usuarioModelSalvo = usuarioRepository.findOneById(id);

    if (usuarioModelSalvo == null) {
      throw new RequestError(HttpStatus.NOT_FOUND, "Usuário não encontrado.");
    }

    usuarioModelSalvo.setNome(usuarioAlteracaoDTO.nome());
    usuarioModelSalvo.setEmail(usuarioAlteracaoDTO.email());

    if (usuarioAlteracaoDTO.senha() != null) {
      usuarioModelSalvo.setSenha(usuarioAlteracaoDTO.senha());
    }

    this.validaCredenciaisUsuario(usuarioModelSalvo.getEmail(), usuarioModelSalvo.getId());

    final UsuarioModel usuarioModelAtualizado = usuarioRepository.save(usuarioModelSalvo);

    return usuarioMapper.toUsuarioRespostaDTO(usuarioModelAtualizado);
  }

  public UsuarioDetalhesDTO deletaUsuario(Long id) {
    UsuarioDetalhesDTO usuarioDetalhesDTO = this.buscaUsuario(id);
    int qtdNotasUsuario = notaRepository.countByUsuarioId(id);

    if (qtdNotasUsuario > 0) {
      throw new RequestError(HttpStatus.CONFLICT, "O usuário possui " + qtdNotasUsuario + " nota(s) vinculadas.");
    }

    usuarioRepository.deleteById(id);

    return usuarioDetalhesDTO;
  }

  public void validaCredenciaisUsuario(String login, Long id) {
    final UsuarioModel usuarioModel = usuarioRepository.findOneByEmail(login);

    if (usuarioModel != null && !usuarioModel.getId().equals(id)) {
      throw new RequestError(HttpStatus.CONFLICT, "Já existe um usuário com este e-mail cadastrado.");
    }
  }
}