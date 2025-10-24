package com.example.ac2sistema.services;

import java.util.List;

import com.example.ac2sistema.dtos.FuncionarioDTO;
import com.example.ac2sistema.dtos.FuncionarioRequestDTO;

public interface FuncionarioService {

    void salvar(FuncionarioRequestDTO funcionarioRequestDTO);

    FuncionarioDTO obterPorId(Integer id);


    
}
