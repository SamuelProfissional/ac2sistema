package com.example.ac2sistema.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ac2sistema.dtos.FuncionarioDTO;
import com.example.ac2sistema.services.FuncionarioService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {


    private FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    public void adicionarFuncionario(@RequestBody FuncionarioDTO funcionarioDTO) {
        funcionarioService.salvar(funcionarioDTO);
    }

    @GetMapping("{id}")
    public FuncionarioDTO buscarProjeto(@PathVariable Integer id) {
        return funcionarioService.obterPorId(id);
    }


}
