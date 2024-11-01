package com.satc.todolist.repositories;

import com.satc.todolist.models.UsuarioModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
  UsuarioModel findOneById(Long id);
  UsuarioModel findOneByEmail(String email);
}