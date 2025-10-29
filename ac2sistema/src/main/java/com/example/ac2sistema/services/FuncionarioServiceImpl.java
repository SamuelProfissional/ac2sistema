package com.example.ac2sistema.services;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ac2sistema.dtos.DadosProjetoDTO;
import com.example.ac2sistema.dtos.FuncionarioDTO;
import com.example.ac2sistema.dtos.RegraNegocioException;
import com.example.ac2sistema.models.Funcionario;
import com.example.ac2sistema.models.Projeto;
import com.example.ac2sistema.models.Setor;
import com.example.ac2sistema.repositories.FuncionarioRepository;
import com.example.ac2sistema.repositories.SetorRepository;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final SetorRepository setorRepository;

    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository, SetorRepository setorRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.setorRepository = setorRepository;
    }

    @Override
    @Transactional
    public void salvar(FuncionarioDTO funcionarioDTO) {
        validarFuncionario(funcionarioDTO);

        Funcionario funcionario = Funcionario.builder()
                .nome(funcionarioDTO.getNome().trim())
                .build();

        if (funcionarioDTO.getSetorId() != null) {
            Setor setor = setorRepository.findById(funcionarioDTO.getSetorId())
                    .orElseThrow(() -> new RegraNegocioException("Setor nao encontrado."));
            funcionario.setSetor(setor);
        }

        funcionarioRepository.save(funcionario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DadosProjetoDTO> buscarProjetos(Integer id) {
        if (id == null) {
            throw new RegraNegocioException("Id do funcionario obrigatorio.");
        }

        if (!funcionarioRepository.existsById(id)) {
            throw new RegraNegocioException("Funcionario nao encontrado.");
        }

        return funcionarioRepository.findProjetosByFuncionarioId(id)
                .stream()
                .map(this::toDadosProjetoDTO)
                .collect(Collectors.toList());
    }

    private void validarFuncionario(FuncionarioDTO funcionarioDTO) {
        if (funcionarioDTO == null) {
            throw new RegraNegocioException("Funcionario obrigatorio.");
        }

        if (funcionarioDTO.getNome() == null || funcionarioDTO.getNome().isBlank()) {
            throw new RegraNegocioException("Nome obrigatorio.");
        }
    }

    private DadosProjetoDTO toDadosProjetoDTO(Projeto projeto) {
        List<String> funcionarios = projeto.getFuncionarios() == null
                ? Collections.emptyList()
                : projeto.getFuncionarios().stream()
                        .filter(Objects::nonNull)
                        .map(Funcionario::getNome)
                        .collect(Collectors.toList());

        return DadosProjetoDTO.builder()
                .id(projeto.getId())
                .descricao(projeto.getDescricao())
                .dataInicio(projeto.getDataInicio())
                .dataFim(projeto.getDataFim())
                .funcionarios(funcionarios)
                .build();
    }
}
