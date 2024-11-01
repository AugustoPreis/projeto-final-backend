package com.satc.todolist.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "notas")
public class NotaModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column()
  private Long id;

  @ManyToOne()
  private CategoriaNotaModel categoriaNota;

  @ManyToOne()
  private UsuarioModel usuario;

  @Column(length = 100)
  private String titulo;

  @Column()
  private String descricao;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public CategoriaNotaModel getCategoriaNota() {
    return categoriaNota;
  }

  public void setCategoriaNota(CategoriaNotaModel categoriaNota) {
    this.categoriaNota = categoriaNota;
  }

  public UsuarioModel getUsuario() {
    return usuario;
  }

  public void setUsuario(UsuarioModel usuario) {
    this.usuario = usuario;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }
}