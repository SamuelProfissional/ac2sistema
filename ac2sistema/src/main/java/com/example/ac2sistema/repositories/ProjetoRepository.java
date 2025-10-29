package com.example.ac2sistema.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.ac2sistema.dtos.ProjetoDTO;
import com.example.ac2sistema.dtos.RegraNegocioException;
import com.example.ac2sistema.models.Projeto;
import java.time.LocalDate;


public interface  ProjetoRepository extends JpaRepository <Projeto, Integer>{

   @Query("SELECT p FROM Projeto p LEFT JOIN FETCH p.funcionarios WHERE p.id =:id")
List<Projeto> findByIdFetchFuncionarios(Integer id);   

List<Projeto> findByDataInicioBetween(LocalDate dataInicio, LocalDate dataFim);

@Transactional
default Projeto salvar(ProjetoDTO dto) {
    if (dto == null || dto.getDescricao() == null || dto.getDataInicio() == null || dto.getDataFim() == null) {
        throw new RegraNegocioException("Dados do projeto inválidos.");
    }
    Projeto p = Projeto.builder()
            .descricao(dto.getDescricao())
            .dataInicio(dto.getDataInicio())
            .dataFim(dto.getDataFim())
            .build();
    return save(p);
}
 
    
}
