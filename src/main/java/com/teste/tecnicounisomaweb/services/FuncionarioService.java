package com.teste.tecnicounisomaweb.services;


import com.teste.tecnicounisomaweb.models.Endereco;
import com.teste.tecnicounisomaweb.models.Funcionario;
import com.teste.tecnicounisomaweb.models.dto.FuncionarioDTO;
import com.teste.tecnicounisomaweb.repositories.FuncionarioRepository;
import com.teste.tecnicounisomaweb.services.errors.FuncionarioExistenteError;
import com.teste.tecnicounisomaweb.services.errors.RecursoNaoEncontradoError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    EnderecoService enderecoService;

    public List<Funcionario> getFuncionarios(){
        return this.funcionarioRepository.findAll();
    }

    public Funcionario salvarFuncionario(FuncionarioDTO dto) {

        Optional<Funcionario> funcionarioExistente = funcionarioRepository.findByCpf(dto.getCpf());

        if (funcionarioExistente.isPresent()) {
            throw new FuncionarioExistenteError("Funcionário já existente.");
        } else {
            Endereco endereco = enderecoService.buscarEndereco(dto.getEndereco().getCep());
            Funcionario funcionario = new Funcionario(
                    null,
                    dto.getNome(),
                    dto.getCpf(),
                    LocalDate.parse(dto.getDataNascimento().toString()),
                    dto.getTelefone(),
                    dto.getSalario()
            );
            funcionario.setEndereco(endereco);

            return funcionarioRepository.save(funcionario);
        }


    }
}
