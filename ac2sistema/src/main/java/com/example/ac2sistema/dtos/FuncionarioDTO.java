package com.example.ac2sistema.dtos;

import com.example.ac2sistema.models.Projeto;
import com.example.ac2sistema.models.Setor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class FuncionarioDTO {
    private Integer id;
    private String nome;  
    private Setor setor;
    private Projeto projeto;
}
