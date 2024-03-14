package com.teste.tecnicounisomaweb.models;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "funcionarios")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private Integer idFuncionario;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(nullable = false, length = 10)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @Column(nullable = false, unique = true, length = 15)
    private String telefone;
    @Column(nullable = false)
    private Double salario;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="EnderecoFK")
    private Endereco endereco;

    public Funcionario(){}
    public Funcionario(
            Integer idFuncionario,
            String nome,
            String cpf,
            LocalDate dataNascimento,
            String telefone,
            Double salario,
            Endereco endereco) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.salario = salario;
        this.endereco = endereco;
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Funcionario)) return false;
        Funcionario that = (Funcionario) o;
        return Objects.equals(getIdFuncionario(), that.getIdFuncionario()) && Objects.equals(getNome(), that.getNome()) && Objects.equals(getCpf(), that.getCpf()) && Objects.equals(getDataNascimento(), that.getDataNascimento()) && Objects.equals(getTelefone(), that.getTelefone()) && Objects.equals(getSalario(), that.getSalario()) && Objects.equals(getEndereco(), that.getEndereco());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdFuncionario(), getNome(), getCpf(), getDataNascimento(), getTelefone(), getSalario(), getEndereco());
    }
}
