package com.teste.tecnicounisomaweb.services.errors;

public class FuncionarioExistenteError extends RuntimeException {
    public FuncionarioExistenteError(String message) {
        super(message);
    }
}
