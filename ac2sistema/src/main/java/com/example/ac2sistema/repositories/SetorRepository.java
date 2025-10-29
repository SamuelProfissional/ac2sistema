package com.example.ac2sistema.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ac2sistema.dtos.RegraNegocioException;
import com.example.ac2sistema.dtos.SetorDTO;
import com.example.ac2sistema.models.Setor;

import jakarta.transaction.Transactional;

public interface SetorRepository extends JpaRepository<Setor, Integer> {

@Query("SELECT s FROM Setor s LEFT JOIN FETCH s.funcionarios WHERE s.id =:id")
List<Setor> findByIdFetchSetores(Integer id);

@Transactional
default Setor salvar(SetorDTO dto) {
    if (dto == null || dto.getNome() == null || dto.getNome().isBlank()) {
        throw new RegraNegocioException("Nome do setor inválido.");
    }
    Setor s = Setor.builder().nome(dto.getNome()).build();
    return save(s);
}   

}
