package com.example.ac2sistema.services;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ac2sistema.dtos.DadosProjetoDTO;
import com.example.ac2sistema.dtos.ProjetoDTO;
import com.example.ac2sistema.dtos.RegraNegocioException;
import com.example.ac2sistema.models.Funcionario;
import com.example.ac2sistema.models.Projeto;
import com.example.ac2sistema.repositories.FuncionarioRepository;
import com.example.ac2sistema.repositories.ProjetoRepository;

@Service
public class ProjetoServiceImpl implements ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final FuncionarioRepository funcionarioRepository;

    public ProjetoServiceImpl(ProjetoRepository projetoRepository, FuncionarioRepository funcionarioRepository) {
        this.projetoRepository = projetoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public DadosProjetoDTO obterPorId(Integer id) {
        if (id == null) {
            throw new RegraNegocioException("Id do projeto obrigatorio.");
        }

        Projeto projeto = projetoRepository.findDetalhadoPorId(id)
                .orElseThrow(() -> new RegraNegocioException("Projeto nao encontrado."));

        return toDadosProjetoDTO(projeto);
    }

    @Override
    @Transactional
    public Projeto salvar(ProjetoDTO dto) {
        validarProjeto(dto);

        Projeto projeto = Projeto.builder()
                .descricao(dto.getDescricao().trim())
                .dataInicio(dto.getDataInicio())
                .dataFim(dto.getDataFim())
                .build();

        return projetoRepository.save(projeto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DadosProjetoDTO> listarTodos() {
        return projetoRepository.findTodosComFuncionarios()
                .stream()
                .map(this::toDadosProjetoDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void vincularFuncionario(Integer idProjeto, Integer idFuncionario) {
        if (idProjeto == null || idFuncionario == null) {
            throw new RegraNegocioException("Ids de projeto e funcionario sao obrigatorios.");
        }

        Projeto projeto = projetoRepository.findDetalhadoPorId(idProjeto)
                .orElseThrow(() -> new RegraNegocioException("Projeto nao encontrado."));

        Funcionario funcionario = funcionarioRepository.findDetalhadoPorId(idFuncionario)
                .orElseGet(() -> funcionarioRepository.findById(idFuncionario)
                        .orElseThrow(() -> new RegraNegocioException("Funcionario nao encontrado.")));

        if (projeto.getFuncionarios().stream()
                .filter(Objects::nonNull)
                .anyMatch(f -> idFuncionario.equals(f.getId()))) {
            return;
        }

        projeto.getFuncionarios().add(funcionario);
        funcionario.getProjetos().add(projeto);

        funcionarioRepository.save(funcionario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DadosProjetoDTO> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        if (dataInicio == null || dataFim == null) {
            throw new RegraNegocioException("Datas de inicio e fim sao obrigatorias.");
        }

        if (dataFim.isBefore(dataInicio)) {
            throw new RegraNegocioException("Data final nao pode ser anterior a data inicial.");
        }

        return projetoRepository.findByDataInicioBetween(dataInicio, dataFim)
                .stream()
                .map(this::toDadosProjetoDTO)
                .collect(Collectors.toList());
    }

    private void validarProjeto(ProjetoDTO dto) {
        if (dto == null) {
            throw new RegraNegocioException("Projeto obrigatorio.");
        }

        if (dto.getDescricao() == null || dto.getDescricao().isBlank()) {
            throw new RegraNegocioException("Descricao obrigatoria.");
        }

        if (dto.getDataInicio() == null) {
            throw new RegraNegocioException("Data de inicio obrigatoria.");
        }

        if (dto.getDataFim() != null && dto.getDataFim().isBefore(dto.getDataInicio())) {
            throw new RegraNegocioException("Data final nao pode ser anterior a data inicial.");
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
