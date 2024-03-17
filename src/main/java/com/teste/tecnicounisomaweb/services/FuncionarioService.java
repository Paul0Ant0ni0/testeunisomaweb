package com.teste.tecnicounisomaweb.services;


import com.teste.tecnicounisomaweb.models.DetalhesAlteracaoSalario;
import com.teste.tecnicounisomaweb.models.Endereco;
import com.teste.tecnicounisomaweb.models.Funcionario;
import com.teste.tecnicounisomaweb.models.dto.FuncionarioDTO;
import com.teste.tecnicounisomaweb.repositories.FuncionarioRepository;
import com.teste.tecnicounisomaweb.services.errors.FuncionarioExistenteError;
import com.teste.tecnicounisomaweb.services.errors.RecursoNaoEncontradoError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashMap;
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

    public DetalhesAlteracaoSalario calcReajusteSalarial(String cpf){
        Funcionario funcionario = this.funcionarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new RecursoNaoEncontradoError("Funcionário não encontrado!!"));

        DetalhesAlteracaoSalario detalhesAtualSalario = new DetalhesAlteracaoSalario();


        if (funcionario.getSalario() >= 0 && funcionario.getSalario() <= 400) {
            detalhesAtualSalario.setReajusteGanho(funcionario.getSalario() * (15.0 / 100.0));
            funcionario.setSalario(funcionario.getSalario() + detalhesAtualSalario.getReajusteGanho());
            detalhesAtualSalario.setEmPercentual("15 %");
        } else if (funcionario.getSalario() > 400 && funcionario.getSalario() <= 800) {
            detalhesAtualSalario.setReajusteGanho(funcionario.getSalario() * (12.0 / 100.0));
            funcionario.setSalario(funcionario.getSalario() + detalhesAtualSalario.getReajusteGanho());
            detalhesAtualSalario.setEmPercentual("12 %");
        } else if (funcionario.getSalario() > 800 && funcionario.getSalario() <= 1200) {
            detalhesAtualSalario.setReajusteGanho(funcionario.getSalario() * (10.0 / 100.0));
            funcionario.setSalario(funcionario.getSalario() + detalhesAtualSalario.getReajusteGanho());
            detalhesAtualSalario.setEmPercentual("10 %");
        } else if (funcionario.getSalario() > 1200 && funcionario.getSalario() <= 2000) {
            detalhesAtualSalario.setReajusteGanho(funcionario.getSalario() * (7.0 / 100.0));
            funcionario.setSalario(funcionario.getSalario() + detalhesAtualSalario.getReajusteGanho());
            detalhesAtualSalario.setEmPercentual("7 %");
        } else if (funcionario.getSalario() > 2000) {
            detalhesAtualSalario.setReajusteGanho(funcionario.getSalario() * (4.0 / 100.0));
            funcionario.setSalario(funcionario.getSalario() + detalhesAtualSalario.getReajusteGanho());
            detalhesAtualSalario.setEmPercentual("4 %");
        }

        detalhesAtualSalario.setCpf(funcionario.getCpf());
        detalhesAtualSalario.setNovoSalario(funcionario.getSalario());
        funcionario.setSalario(BigDecimal.valueOf(funcionario.getSalario())
                .setScale(3, RoundingMode.HALF_EVEN).doubleValue());
        this.funcionarioRepository.save(funcionario);


        return detalhesAtualSalario;
    }

}
