package com.example.ac2sistema.services;

public interface ProjetoService {

    void salvar(CursoRequestDTO cursoRequestDTO);

    CursoDTO obterPorId(Long id);

    void remover(Long id);

    void editar(Long id, CursoRequestDTO cursoRequestDTO);

    List<CursoDTO> obterTodos();
    
}
