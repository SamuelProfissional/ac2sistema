package com.example.ac2sistema.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ac2sistema.models.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {

    @Query("select distinct p from Projeto p left join fetch p.funcionarios where p.id = :id")
    Optional<Projeto> findDetalhadoPorId(Integer id);

    @Query("select distinct p from Projeto p left join fetch p.funcionarios")
    List<Projeto> findTodosComFuncionarios();

    @Query("select distinct p from Projeto p left join fetch p.funcionarios where p.dataInicio between :dataInicio and :dataFim")
    List<Projeto> findByDataInicioBetween(LocalDate dataInicio, LocalDate dataFim);
}
