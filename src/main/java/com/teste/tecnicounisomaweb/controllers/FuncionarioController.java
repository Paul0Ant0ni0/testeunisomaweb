package com.teste.tecnicounisomaweb.controllers;


import com.teste.tecnicounisomaweb.models.Funcionario;
import com.teste.tecnicounisomaweb.models.dto.FuncionarioDTO;
import com.teste.tecnicounisomaweb.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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



}
