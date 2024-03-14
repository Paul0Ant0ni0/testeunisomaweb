package com.teste.tecnicounisomaweb.models.dto;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class EnderecoDTO {

    @NotBlank(message = "O campo cep é obrigatório!")
    @Length(max = 9, message = "Limite de caracteres excedido!")
    private String cep;

    @NotBlank(message = "O campo logradouro é obrigatório!")
    private String logradouro;

    private String complemento;

    @NotBlank(message = "O campo bairro é obrigatório!")
    private String bairro;

    @NotBlank(message = "O campo localidade é obrigatório!")
    private String localidade;

    @NotBlank(message = "O campo uf é obrigatório!")
    @Length(max = 9, message = "Limite de caracteres excedido!")
    private String uf;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
