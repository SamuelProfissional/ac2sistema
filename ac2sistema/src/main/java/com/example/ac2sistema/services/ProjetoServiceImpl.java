package com.example.ac2sistema.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ac2sistema.dtos.DadosProjetoDTO;
import com.example.ac2sistema.dtos.ProjetoDTO;
import com.example.ac2sistema.dtos.RegraNegocioException;
import com.example.ac2sistema.models.Projeto;
import com.example.ac2sistema.repositories.ProjetoRepository;

@Service
public class ProjetoServiceImpl implements ProjetoService {

     public ProjetoRepository projetoRepository;


    public ProjetoServiceImpl(ProjetoRepository projetoRepository){

        this.projetoRepository = projetoRepository;
    

    }

    public DadosProjetoDTO obterPorId(Integer id) {
        Projeto p = projetoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Projeto não encontrado."));
        return new DadosProjetoDTO();
    }

    @Transactional
    public Projeto salvar(ProjetoDTO dto) {
        return projetoRepository.salvar(dto);
    }
    
}
