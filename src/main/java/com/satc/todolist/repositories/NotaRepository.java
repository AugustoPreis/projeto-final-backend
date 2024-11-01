package com.satc.todolist.repositories;

import com.satc.todolist.models.NotaModel;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaRepository extends JpaRepository<NotaModel, Long> {
  NotaModel findOneById(Long id);

  List<NotaModel> findAllByUsuarioId(Long usuarioId, Sort ordem);

  NotaModel findOneByIdAndUsuarioId(Long id, Long usuarioId);
}