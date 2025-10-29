package com.example.ac2sistema.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ac2sistema.models.Setor;

public interface SetorRepository extends JpaRepository<Setor, Integer> {

    @Query("select distinct s from Setor s left join fetch s.funcionarios where s.id = :id")
    Optional<Setor> findDetalhadoPorId(Integer id);

    @Query("select distinct s from Setor s left join fetch s.funcionarios")
    List<Setor> findTodosComFuncionarios();
}
