package com.example.ac2sistema.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioRequestDTO {
    private Integer id;
    private String nome;  
    
    private Integer idSetor;
    private Integer idProjeto;
}
