package com.example.ac2sistema.services;

import com.example.ac2sistema.dtos.DadosSetorDTO;
import com.example.ac2sistema.dtos.SetorDTO;
import com.example.ac2sistema.models.Setor;

public interface SetorService {

    //ok
    Setor salvar(SetorDTO setorDTO);

    DadosSetorDTO obterPorId(Integer id);
   
    
}
