package com.satc.todolist.controllers;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.satc.todolist.dtos.RequestErrorDTO;
import com.satc.todolist.dtos.UsuarioAlteracaoDTO;
import com.satc.todolist.dtos.UsuarioDetalhesDTO;
import com.satc.todolist.dtos.UsuarioCadastroDTO;
import com.satc.todolist.dtos.UsuarioRespostaDTO;
import com.satc.todolist.services.UsuarioService;
import com.satc.todolist.utils.ErrorUtils;
import com.satc.todolist.utils.RequestError;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
  @Autowired
  UsuarioService usuarioService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public List<UsuarioRespostaDTO> listaUsuarios() {
    return usuarioService.listaUsuarios();
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

  @ExceptionHandler(RequestError.class)
  @ResponseBody
  public RequestErrorDTO erro(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, RequestError requestError) {
    httpServletResponse.setStatus(requestError.getStatus().value());

    return new RequestErrorDTO(requestError.getStatus(), new String[] { requestError.getMessage() });
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public RequestErrorDTO erroValidacao(HttpServletRequest httpServletRequest,
      MethodArgumentNotValidException exception) {
    String[] messages = ErrorUtils.getMessages(exception.getBindingResult().getAllErrors());

    return new RequestErrorDTO(HttpStatus.BAD_REQUEST, messages);
  }
}