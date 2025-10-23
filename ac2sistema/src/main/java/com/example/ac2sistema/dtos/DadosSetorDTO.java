package com.example.ac2sistema.dtos;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DadosSetorDTO {
    private Integer id;
    private String nome;  
    List<String> funcionarios;
}
