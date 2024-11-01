package com.satc.todolist.repositories;

import com.satc.todolist.models.CategoriaNotaModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaNotaRepository extends JpaRepository<CategoriaNotaModel, Long> {
  CategoriaNotaModel findOneById(Long id);
}