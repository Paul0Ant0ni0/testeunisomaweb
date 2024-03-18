package com.teste.tecnicounisomaweb.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.teste.tecnicounisomaweb.models.Endereco;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class FuncionarioDTO {


    @NotBlank(message = "O campo nome é obrigatório!")
    @Length(max = 150, message = "Limite de caracteres excedido!")
    private String nome;

    @NotBlank(message = "O campo CPF é obrigatório!")
    @Size(min = 11, max = 14, message = "O CPF deve conter entre 11 e 14 caracteres!")
    private String cpf;

    @NotNull(message = "Campo data de nascimento é obrigatório!")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @NotBlank(message = "O campo telefone é obrigatório")
    @Length(max = 14, message = "Limite de caracteres excedido!")
    private String telefone;

    @NotNull(message = "O campo salário é obrigatório.")
    @Min(value = 0, message = "Limite de caracteres insuficiente!")
    private Double salario;

    @NotNull(message = "O campo Endereço é obrigatório")
    private Endereco endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
