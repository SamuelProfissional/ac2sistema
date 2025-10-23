package com.example.ac2sistema.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ac2sistema.models.Projeto;
import java.time.LocalDate;


public interface  ProjetoRepository extends JpaRepository <Projeto, Integer>{

   @Query("SELECT p FROM Projeto p LEFT JOIN FETCH p.funcionarios WHERE p.id =:id")
List<Projeto> findByIdFetchFuncionarios(Integer id);   

List<Projeto> findByDataInicioBetween(LocalDate dataInicio, LocalDate dataFim);
 
    
}
