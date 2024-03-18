package com.teste.tecnicounisomaweb.controllers;


import com.teste.tecnicounisomaweb.models.Endereco;
import com.teste.tecnicounisomaweb.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnderecoController {


    @Autowired
    EnderecoService enderecoService;


    @GetMapping("/api/{cep}")
    public Endereco getEndereco(@PathVariable String cep) {
        return this.enderecoService.buscarEnderecoApi(cep);
    }

}
