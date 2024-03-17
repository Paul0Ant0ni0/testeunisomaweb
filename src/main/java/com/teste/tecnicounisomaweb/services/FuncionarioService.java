package com.teste.tecnicounisomaweb.services;


import com.teste.tecnicounisomaweb.models.DetalhesAlteracaoSalario;
import com.teste.tecnicounisomaweb.models.DetalhesImpostoRenda;
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

        final Double salario = funcionario.getSalario();

        if (salario >= 0 && salario<= 400) {
            detalhesAtualSalario.setReajusteGanho(salario * (15.0 / 100.0));
            funcionario.setSalario(salario + detalhesAtualSalario.getReajusteGanho());
            detalhesAtualSalario.setEmPercentual("15 %");
        } else if (salario > 400 && salario <= 800) {
            detalhesAtualSalario.setReajusteGanho(salario * (12.0 / 100.0));
            funcionario.setSalario(salario + detalhesAtualSalario.getReajusteGanho());
            detalhesAtualSalario.setEmPercentual("12 %");
        } else if (salario> 800 && salario<= 1200) {
            detalhesAtualSalario.setReajusteGanho(salario * (10.0 / 100.0));
            funcionario.setSalario(salario + detalhesAtualSalario.getReajusteGanho());
            detalhesAtualSalario.setEmPercentual("10 %");
        } else if (salario > 1200 && salario <= 2000) {
            detalhesAtualSalario.setReajusteGanho(salario * (7.0 / 100.0));
            funcionario.setSalario(salario + detalhesAtualSalario.getReajusteGanho());
            detalhesAtualSalario.setEmPercentual("7 %");
        } else if (salario > 2000) {
            detalhesAtualSalario.setReajusteGanho(salario * (4.0 / 100.0));
            funcionario.setSalario(salario + detalhesAtualSalario.getReajusteGanho());
            detalhesAtualSalario.setEmPercentual("4 %");
        }

        detalhesAtualSalario.setCpf(funcionario.getCpf());
        detalhesAtualSalario.setNovoSalario(funcionario.getSalario());
        funcionario.setSalario(BigDecimal.valueOf(funcionario.getSalario())
                .setScale(3, RoundingMode.HALF_EVEN).doubleValue());
        this.funcionarioRepository.save(funcionario);


        return detalhesAtualSalario;
    }

    public DetalhesImpostoRenda calcRImpostoRenda(String cpf){
        Funcionario funcionario = this.funcionarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new RecursoNaoEncontradoError("Funcionário não encontrado!!"));

        DetalhesImpostoRenda detalhesImpostoRenda = new DetalhesImpostoRenda();
        detalhesImpostoRenda.setCpf(funcionario.getCpf());
        Double salario = funcionario.getSalario();
        double imposto;

        if (salario <= 2000.00) {
            detalhesImpostoRenda.setValorDoImposto(0.0);
        } else if (salario <= 3000.00) {
            imposto = (salario - 2000.00) * 0.08;
            detalhesImpostoRenda.setValorDoImposto(imposto);
        } else if (salario <= 4500.00) {
            imposto = (1000.00 * 0.08) + ((salario - 3000.00) * 0.18);
            detalhesImpostoRenda.setValorDoImposto(imposto);
        } else {
            imposto = (1000.00 * 0.08) + (1500.00 * 0.18) + ((salario - 4500.00) * 0.28);
            detalhesImpostoRenda.setValorDoImposto(imposto);
        }

        return detalhesImpostoRenda;
    }

}
