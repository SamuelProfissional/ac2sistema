package com.example.ac2sistema.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.ac2sistema.dtos.DadosSetorDTO;
import com.example.ac2sistema.dtos.SetorDTO;
import com.example.ac2sistema.services.SetorService;

@RestController
@RequestMapping("/setores")
public class SetorController {

    private final SetorService setorService;

    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }

    @PostMapping
    public void adicionar(@RequestBody SetorDTO setorDTO) {
        setorService.salvar(setorDTO);
    }

    @GetMapping("/{id}")
    public DadosSetorDTO buscarSetorPorId(@PathVariable Integer id) {
        return setorService.obterPorId(id);
    }

    @GetMapping
    public List<DadosSetorDTO> listar() {
        return setorService.listarTodos();
    }
}
