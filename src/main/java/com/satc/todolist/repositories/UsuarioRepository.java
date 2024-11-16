package com.satc.todolist.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.satc.todolist.models.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
  UsuarioModel findOneById(Long id);
  UsuarioModel findOneByEmail(String email);

  @Override
  @Query("SELECT u FROM UsuarioModel u ORDER BY id DESC")
  Page<UsuarioModel> findAll(Pageable pageable);
}