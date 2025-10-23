package com.example.ac2sistema.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ac2sistema.models.Setor;

public interface SetorRepository extends JpaRepository<Setor, Integer> {

@Query("SELECT s FROM Setor s LEFT JOIN FETCH s.funcionarios WHERE s.id =:id")
List<Setor> findByIdFetchSetores(Integer id);   

}
