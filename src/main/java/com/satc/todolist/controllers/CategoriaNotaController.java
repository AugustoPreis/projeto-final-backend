package com.satc.todolist.controllers;

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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.satc.todolist.dtos.CategoriaNotaCadastroDTO;
import com.satc.todolist.dtos.CategoriaNotaDetalhesDTO;
import com.satc.todolist.dtos.CategoriaNotaRespostaDTO;
import com.satc.todolist.services.CategoriaNotaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias-nota")
public class CategoriaNotaController extends DefaultController {
  @Autowired
  CategoriaNotaService categoriaNotaService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public List<CategoriaNotaRespostaDTO> listaCategoriasNota() {
    return categoriaNotaService.listaCategoriasNota();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public CategoriaNotaDetalhesDTO buscaCategoriaNota(@PathVariable("id") Long id) {
    return categoriaNotaService.buscaCategoriaNota(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public CategoriaNotaRespostaDTO criaCategoriaNota(
      @RequestBody @Valid CategoriaNotaCadastroDTO categoriaNotaCadastroDTO) {
    return categoriaNotaService.criaCategoriaNota(categoriaNotaCadastroDTO);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public CategoriaNotaRespostaDTO alteraCategoriaNota(
      @RequestBody @Valid CategoriaNotaCadastroDTO categoriaNotaAlteracaoDTO,
      @PathVariable("id") Long id) {
    return categoriaNotaService.alteraCategoriaNota(id, categoriaNotaAlteracaoDTO);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public CategoriaNotaDetalhesDTO deletaCategoriaNota(@PathVariable("id") Long id) {
    return categoriaNotaService.deletaCategoriaNota(id);
  }
}