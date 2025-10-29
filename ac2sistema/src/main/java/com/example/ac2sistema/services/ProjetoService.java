package com.example.ac2sistema.services;

import com.example.ac2sistema.dtos.DadosProjetoDTO;
import com.example.ac2sistema.dtos.ProjetoDTO;
import com.example.ac2sistema.models.Projeto;

public interface ProjetoService {

   Projeto salvar(ProjetoDTO projetoDTO);

   DadosProjetoDTO obterPorId(Integer id);   
    
}
