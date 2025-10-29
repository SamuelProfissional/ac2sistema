package com.example.ac2sistema.services;

import java.util.List;

import com.example.ac2sistema.dtos.DadosSetorDTO;
import com.example.ac2sistema.dtos.SetorDTO;
import com.example.ac2sistema.models.Setor;

public interface SetorService {

    Setor salvar(SetorDTO setorDTO);

    DadosSetorDTO obterPorId(Integer id);

    List<DadosSetorDTO> listarTodos();
}
