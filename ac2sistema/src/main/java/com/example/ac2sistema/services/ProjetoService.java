package com.example.ac2sistema.services;

import com.example.ac2sistema.dtos.ProjetoDTO;

public interface ProjetoService {

    void salvar(ProjetoDTO projetoDTO);

    ProjetoDTO obterPorId(Integer id);
    //(retorna também a lista de funcionários vinculados)
   
    
}
