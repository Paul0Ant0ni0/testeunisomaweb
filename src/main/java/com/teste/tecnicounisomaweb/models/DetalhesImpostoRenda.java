package com.teste.tecnicounisomaweb.models;

import java.text.DecimalFormat;

public class DetalhesImpostoRenda {

    private String cpf;
    private String valorDoImposto;


    public DetalhesImpostoRenda(){

    }

    public DetalhesImpostoRenda(String cpf, String valorDoImposto) {
        this.cpf = cpf;
        this.valorDoImposto = valorDoImposto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getValorDoImposto() {
        return valorDoImposto;
    }

    public void setValorDoImposto(Double valorDoImposto) {
        if (valorDoImposto == 0.00){
            this.valorDoImposto = "Insento!";
        }else {
            this.valorDoImposto = "Imposto: R$ " + convertRoundNumber(valorDoImposto);
        }
    }

    public String convertRoundNumber(Double valor) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(valor);
    }
}