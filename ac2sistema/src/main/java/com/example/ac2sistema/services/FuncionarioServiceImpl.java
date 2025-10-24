package com.example.ac2sistema.services;

import org.springframework.stereotype.Service;

import com.example.ac2sistema.dtos.FuncionarioRequestDTO;
import com.example.ac2sistema.dtos.RegraNegocioException;
import com.example.ac2sistema.models.Funcionario;
import com.example.ac2sistema.models.Setor;
import com.example.ac2sistema.repositories.FuncionarioRepository;
import com.example.ac2sistema.repositories.ProjetoRepository;
import com.example.ac2sistema.repositories.SetorRepository;

@Service
public class FuncionarioServiceImpl implements FuncionarioService{
// terminar 

    private FuncionarioRepository funcionarioRepository;
    private ProjetoRepository projetoRepository;
    private SetorRepository setorRepository;

    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository, 
    ProjetoRepository projetoRepository, SetorRepository setorRepository){

        this.funcionarioRepository = funcionarioRepository;
        this.projetoRepository = projetoRepository;
        this.setorRepository = setorRepository;

    }

    @Override
    public void salvar(FuncionarioRequestDTO funcionarioRequestDTO) {

        Setor setor = setorRepository.findById(funcionarioRequestDTO.getIdSetor())
                .orElseThrow(() -> new RegraNegocioException("Setor não encontrada."));

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioRequestDTO.getNome());
        // tentar salvar projeto funcionario.set(funcionarioRequestDTO.getCargaHoraria());
        funcionario.setSetor(setor);
        funcionarioRepository.save(funcionario);
        
    }

    // DTO transferencia de dados
       @Override
    public FuncionarioDTO obterPorId(Integer id){
        return funcionarioRepository.findById(id)
        .map((Funcionario f)->{
            return FuncionarioDTO.builder()
            .id(f.getId())
            .nome(f.getNome())
            .cargaHoraria(f.getCargaHoraria())
            .projeto(
                // corrigir e modificar  terminar os getts
                DadosProjetoDTO.builder()
            .id(f.get     ().getId())
            .nome(f.getCategoriaCurso   ().getNome())
            .build()
            
            )
            .build();
        }
        
        ).orElseThrow(
            () -> 
            new RegraNegocioException("Funcionario não encontrado"));
    }


    
    
}
