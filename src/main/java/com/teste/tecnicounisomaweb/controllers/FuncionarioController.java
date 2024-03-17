package com.teste.tecnicounisomaweb.controllers;


import com.teste.tecnicounisomaweb.models.DetalhesAlteracaoSalario;
import com.teste.tecnicounisomaweb.models.DetalhesImpostoRenda;
import com.teste.tecnicounisomaweb.models.Funcionario;
import com.teste.tecnicounisomaweb.models.dto.FuncionarioDTO;
import com.teste.tecnicounisomaweb.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

    @GetMapping("/api/funcionarios")
    public List<Funcionario> listarFuncionarios(){
        return this.funcionarioService.getFuncionarios();
    }

    @PostMapping("/api/funcionario")
    public Funcionario salvarFuncionario(@Valid @RequestBody FuncionarioDTO dto){
        return this.funcionarioService.salvarFuncionario(dto);
    }

    @GetMapping("/api/funcionario/reajustesalarial/{cpf}")
    public DetalhesAlteracaoSalario calcReajusteSalarial(@PathVariable String cpf){
        return this.funcionarioService.calcReajusteSalarial(cpf);
    }


    @GetMapping("/api/funcionario/calcimposto/{cpf}")
    public DetalhesImpostoRenda calcImpostoDeRenda(@PathVariable String cpf){
        return this.funcionarioService.calcRImpostoRenda(cpf);
    }

}
