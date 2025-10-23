package com.example.ac2sistema.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ac2sistema.models.Funcionario;

public interface  FuncionarioRepository extends JpaRepository <Funcionario, Integer> {

@Query("SELECT f FROM Funcionario f LEFT JOIN FETCH f.projetos WHERE f.id =:id")
List<Funcionario> findByIdFetchFuncionarios(Integer id);   

}
