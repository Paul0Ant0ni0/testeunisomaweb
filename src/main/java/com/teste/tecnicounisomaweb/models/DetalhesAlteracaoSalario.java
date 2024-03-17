package com.teste.tecnicounisomaweb.models;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DetalhesAlteracaoSalario {
    private String cpf;

    private Double novoSalario;

    private Double reajusteGanho;

    private String emPercentual;

    public DetalhesAlteracaoSalario(){}

    public DetalhesAlteracaoSalario(String cpf, Double novoSalario, Double reajusteGanho, String emPercentual) {
        this.cpf = cpf;
        this.novoSalario = novoSalario;
        this.reajusteGanho = reajusteGanho;
        this.emPercentual = emPercentual;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getNovoSalario() {
        return novoSalario;
    }

    public void setNovoSalario(Double novoSalario) {
        this.novoSalario = this.convertRoundNumber(novoSalario);
    }

    public Double getReajusteGanho() {
        return reajusteGanho;
    }

    public void setReajusteGanho(Double reajusteGanho) {
        this.reajusteGanho = this.convertRoundNumber(reajusteGanho);
    }

    public String getEmPercentual() {
        return emPercentual;
    }

    public void setEmPercentual(String emPercentual) {
        this.emPercentual = emPercentual;
    }

    public Double convertRoundNumber(Double valor){
        BigDecimal roundNumber = new BigDecimal(valor).setScale(3, RoundingMode.HALF_EVEN);
        return roundNumber.doubleValue();
    }
}
