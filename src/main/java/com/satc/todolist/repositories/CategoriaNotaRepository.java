package com.satc.todolist.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.satc.todolist.models.CategoriaNotaModel;

@Repository
public interface CategoriaNotaRepository extends JpaRepository<CategoriaNotaModel, Long> {
  CategoriaNotaModel findOneById(Long id);

  @Override
  @Query("SELECT n FROM CategoriaNotaModel n ORDER BY id DESC")
  Page<CategoriaNotaModel> findAll(Pageable pageable);
}