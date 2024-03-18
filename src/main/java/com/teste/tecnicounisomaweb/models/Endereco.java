package com.teste.tecnicounisomaweb.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "enderecos")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private Integer idEndereco;

    @Column(nullable = false, unique = true, length = 9)
    private String cep;

    @Column(nullable = false)
    private String logradouro;

    private String complemento;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String localidade;

    @Column(nullable = false, length = 2)
    private String uf;

    public Endereco(){}

    public Endereco(Integer idEndereco, String cep, String logradouro, String complemento, String bairro, String localidade, String uf) {
        this.idEndereco = idEndereco;
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
    }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Endereco)) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(getIdEndereco(), endereco.getIdEndereco()) && Objects.equals(getCep(), endereco.getCep()) && Objects.equals(getLogradouro(), endereco.getLogradouro()) && Objects.equals(getComplemento(), endereco.getComplemento()) && Objects.equals(getBairro(), endereco.getBairro()) && Objects.equals(getLocalidade(), endereco.getLocalidade()) && Objects.equals(getUf(), endereco.getUf());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdEndereco(), getCep(), getLogradouro(), getComplemento(), getBairro(), getLocalidade(), getUf());
    }
}
