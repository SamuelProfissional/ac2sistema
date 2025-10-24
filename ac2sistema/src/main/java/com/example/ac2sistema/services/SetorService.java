package com.example.ac2sistema.services;

import com.example.ac2sistema.dtos.ProjetoDTO;
import com.example.ac2sistema.dtos.SetorDTO;

public interface SetorService {

    //ok
    void salvar(SetorDTO setorDTO);

    ProjetoDTO obterPorId(Integer id);
   
    
}
