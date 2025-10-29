package com.example.ac2sistema.services;

import java.util.List;

import com.example.ac2sistema.dtos.DadosProjetoDTO;
import com.example.ac2sistema.dtos.FuncionarioDTO;

public interface FuncionarioService {

    void salvar(FuncionarioDTO funcionarioDTO);

    List<DadosProjetoDTO> buscarProjetos(Integer id);
}
