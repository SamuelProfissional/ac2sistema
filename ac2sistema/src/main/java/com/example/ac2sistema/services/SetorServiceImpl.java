package com.example.ac2sistema.services;

import org.springframework.stereotype.Service;

import com.example.ac2sistema.dtos.DadosSetorDTO;
import com.example.ac2sistema.dtos.RegraNegocioException;
import com.example.ac2sistema.dtos.SetorDTO;
import com.example.ac2sistema.models.Setor;
import com.example.ac2sistema.repositories.SetorRepository;

import jakarta.transaction.Transactional;

@Service
public class SetorServiceImpl implements SetorService{

    public SetorRepository setorRepository;


    public SetorServiceImpl(SetorRepository setorRepository){

        this.setorRepository = setorRepository;
    
    }
    @Transactional
    public Setor salvar(SetorDTO dto) {
        return setorRepository.salvar(dto);
    }

    public DadosSetorDTO obterPorId(Integer id) {
        Setor s = setorRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Setor não encontrado."));
        return new DadosSetorDTO();
    }
    
}
