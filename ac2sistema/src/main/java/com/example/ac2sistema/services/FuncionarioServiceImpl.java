package com.example.ac2sistema.services;

import org.springframework.stereotype.Service;

import com.example.ac2sistema.dtos.FuncionarioDTO;
import com.example.ac2sistema.dtos.RegraNegocioException;
import com.example.ac2sistema.models.Funcionario;
import com.example.ac2sistema.repositories.FuncionarioRepository;

import jakarta.transaction.Transactional;

@Service
public class FuncionarioServiceImpl implements FuncionarioService{
// terminar 

    private FuncionarioRepository funcionarioRepository;


    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository){

        this.funcionarioRepository = funcionarioRepository;
    

    }

    @Override
    @Transactional
    public void salvar(FuncionarioDTO funcionarioDTO) {
        if (funcionarioDTO == null || funcionarioDTO.getNome() == null || funcionarioDTO.getNome().isBlank()) {
            throw new RegraNegocioException("Nome inválido.");
        }

        Funcionario f = Funcionario.builder()
                .nome(funcionarioDTO.getNome())
                .build();

        funcionarioRepository.save(f);
    }

    @Override
    public FuncionarioDTO obterPorId(Integer id) {
        if (id == null) {
            throw new RegraNegocioException("Id inválido.");
        }

        Funcionario f = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Funcionário não encontrado."));

        return toDTO(f);
    }

    private FuncionarioDTO toDTO(Funcionario f) {
        return FuncionarioDTO.builder()
                .id(f.getId())
                .nome(f.getNome())
                .build();
    }




    
    
}
