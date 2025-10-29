package com.example.ac2sistema.services;

import java.time.LocalDate;
import java.util.List;

import com.example.ac2sistema.dtos.DadosProjetoDTO;
import com.example.ac2sistema.dtos.ProjetoDTO;
import com.example.ac2sistema.models.Projeto;

public interface ProjetoService {

    Projeto salvar(ProjetoDTO projetoDTO);

    DadosProjetoDTO obterPorId(Integer id);

    List<DadosProjetoDTO> listarTodos();

    void vincularFuncionario(Integer idProjeto, Integer idFuncionario);

    List<DadosProjetoDTO> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim);
}
