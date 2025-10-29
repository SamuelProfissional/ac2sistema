package com.example.ac2sistema.services;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ac2sistema.dtos.DadosSetorDTO;
import com.example.ac2sistema.dtos.RegraNegocioException;
import com.example.ac2sistema.dtos.SetorDTO;
import com.example.ac2sistema.models.Funcionario;
import com.example.ac2sistema.models.Setor;
import com.example.ac2sistema.repositories.SetorRepository;

@Service
public class SetorServiceImpl implements SetorService {

    private final SetorRepository setorRepository;

    public SetorServiceImpl(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    @Override
    @Transactional
    public Setor salvar(SetorDTO dto) {
        validarSetor(dto);

        Setor setor = Setor.builder()
                .nome(dto.getNome().trim())
                .build();

        return setorRepository.save(setor);
    }

    @Override
    @Transactional(readOnly = true)
    public DadosSetorDTO obterPorId(Integer id) {
        if (id == null) {
            throw new RegraNegocioException("Id do setor obrigatorio.");
        }

        Setor setor = setorRepository.findDetalhadoPorId(id)
                .orElseThrow(() -> new RegraNegocioException("Setor nao encontrado."));

        return toDadosSetorDTO(setor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DadosSetorDTO> listarTodos() {
        return setorRepository.findTodosComFuncionarios()
                .stream()
                .map(this::toDadosSetorDTO)
                .collect(Collectors.toList());
    }

    private void validarSetor(SetorDTO dto) {
        if (dto == null) {
            throw new RegraNegocioException("Setor obrigatorio.");
        }

        if (dto.getNome() == null || dto.getNome().isBlank()) {
            throw new RegraNegocioException("Nome do setor obrigatorio.");
        }
    }

    private DadosSetorDTO toDadosSetorDTO(Setor setor) {
        List<String> funcionarios = setor.getFuncionarios() == null
                ? Collections.emptyList()
                : setor.getFuncionarios().stream()
                        .filter(Objects::nonNull)
                        .map(Funcionario::getNome)
                        .collect(Collectors.toList());

        return DadosSetorDTO.builder()
                .id(setor.getId())
                .nome(setor.getNome())
                .funcionarios(funcionarios)
                .build();
    }
}
