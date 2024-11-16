package com.satc.todolist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.satc.todolist.dtos.UsuarioAlteracaoDTO;
import com.satc.todolist.dtos.UsuarioCadastroDTO;
import com.satc.todolist.dtos.UsuarioDetalhesDTO;
import com.satc.todolist.dtos.UsuarioRespostaDTO;
import com.satc.todolist.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController extends DefaultController {
  @Autowired
  UsuarioService usuarioService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Page<UsuarioRespostaDTO> listaUsuarios(Pageable pageable) {
    return usuarioService.listaUsuarios(pageable);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public UsuarioDetalhesDTO buscaUsuario(@PathVariable("id") Long id) {
    return usuarioService.buscaUsuario(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public UsuarioRespostaDTO criaUsuario(@RequestBody @Valid UsuarioCadastroDTO usuarioCadastroDTO) {
    return usuarioService.criaUsuario(usuarioCadastroDTO);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public UsuarioRespostaDTO alteraUsuario(@RequestBody @Valid UsuarioAlteracaoDTO usuarioAlteracaoDTO,
      @PathVariable("id") Long id) {
    return usuarioService.alteraUsuario(id, usuarioAlteracaoDTO);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public UsuarioDetalhesDTO deletaUsuario(@PathVariable("id") Long id) {
    return usuarioService.deletaUsuario(id);
  }
}