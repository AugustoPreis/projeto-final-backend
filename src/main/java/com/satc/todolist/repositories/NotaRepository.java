package com.satc.todolist.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.satc.todolist.models.NotaModel;

@Repository
public interface NotaRepository extends JpaRepository<NotaModel, Long> {
  NotaModel findOneById(Long id);

  @Query("SELECT n FROM NotaModel n WHERE n.usuario.id = :usuarioId ORDER BY id DESC")
  Page<NotaModel> findAllByUsuarioId(Long usuarioId,Pageable pageable);

  NotaModel findOneByIdAndUsuarioId(Long id, Long usuarioId);

  int countByUsuarioId(Long usuarioId);
}