package com.example.ac2sistema.services;


import com.example.ac2sistema.dtos.FuncionarioDTO;

public interface FuncionarioService {

    void salvar(FuncionarioDTO funcionarioDTO);

    FuncionarioDTO obterPorId(Integer id);


    
}
