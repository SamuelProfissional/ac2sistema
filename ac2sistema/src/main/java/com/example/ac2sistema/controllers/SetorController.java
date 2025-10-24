package com.example.ac2sistema.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ac2sistema.dtos.DadosProjetoDTO;
import com.example.ac2sistema.dtos.SetorDTO;
import com.example.ac2sistema.services.SetorService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/setor")
public class SetorController {
    
    private SetorService setorService;

    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }

    @PostMapping
    public void adicionarSetor(@RequestBody SetorDTO setorRequestDTO) {
        setorService.salvar(setorRequestDTO);
    }

    @GetMapping("{id}")
    public DadosProjetoDTO buscarSetor(@PathVariable Integer id) {
        return setorService.obterPorId(id);
    }

}
