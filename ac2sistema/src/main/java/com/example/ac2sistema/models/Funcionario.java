package com.example.ac2sistema.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
public class Funcionario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 200, nullable = false)
    private String nome;

    @ManyToOne 
    @JoinColumn(name = "setor_id")
    

    private Setor setor;



@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER) 
    @JoinTable(name = "PROJETO_FUNCIONARIO", joinColumns = {
            @JoinColumn(name = "FUNCIONARIO_ID") }, inverseJoinColumns = { @JoinColumn(name = "PROJETO_ID") })
@ToString.Exclude
    private List<Projeto> projetos;

}
