package com.satc.todolist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.satc.todolist.dtos.NotaAlteracaoDTO;
import com.satc.todolist.dtos.NotaCadastroDTO;
import com.satc.todolist.dtos.NotaDetalhesDTO;
import com.satc.todolist.dtos.NotaRespostaDTO;
import com.satc.todolist.services.NotaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios/{usuarioId}/notas")
public class NotaController extends DefaultController {
  @Autowired
  NotaService notaService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public List<NotaRespostaDTO> listaNotas(@PathVariable Long usuarioId) {
    return notaService.listaNotas(usuarioId);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public NotaDetalhesDTO buscaNota(@PathVariable Long usuarioId, @PathVariable Long id) {
    return notaService.buscaNota(usuarioId, id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public NotaRespostaDTO criaNota(@PathVariable Long usuarioId, @RequestBody @Valid NotaCadastroDTO notaCadastroDTO) {
    return notaService.criaNota(usuarioId, notaCadastroDTO);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public NotaRespostaDTO alteraNota(@PathVariable Long usuarioId, @PathVariable Long id,
      @RequestBody @Valid NotaAlteracaoDTO notaAlteracaoDTO) {
    return notaService.alteraNota(usuarioId, id, notaAlteracaoDTO);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public NotaDetalhesDTO deletaNota(@PathVariable Long usuarioId, @PathVariable Long id) {
    return notaService.deletaNota(usuarioId, id);
  }
}