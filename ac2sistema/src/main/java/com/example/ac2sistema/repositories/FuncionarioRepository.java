package com.example.ac2sistema.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ac2sistema.models.Funcionario;
import com.example.ac2sistema.models.Projeto;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

    @Query("select distinct f from Funcionario f left join fetch f.projetos where f.id = :id")
    Optional<Funcionario> findDetalhadoPorId(Integer id);

    @Query("select distinct p from Funcionario f join f.projetos p left join fetch p.funcionarios where f.id = :id")
    List<Projeto> findProjetosByFuncionarioId(Integer id);
}
