package com.teste.tecnicounisomaweb.services.errors;

// Esta classe representa o erro de regra de negócio
// quando não encontramos cep na api ou banco e funcionariono no banxo
public class RecursoNaoEncontradoError extends RuntimeException {
    public RecursoNaoEncontradoError(String message) {
        super(message);
    }
}
