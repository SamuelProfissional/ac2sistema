package com.example.ac2sistema.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Projeto {

    @Id
    @GeneratedValue 

    private Integer id;
    
    @Column(length = 200, nullable = false)
    private String nome;
    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;
    @Column(name = "data_fim")
    private LocalDate dataFim;

    @ManyToMany(mappedBy = "projetos", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Funcionario> funcionarios;
}
