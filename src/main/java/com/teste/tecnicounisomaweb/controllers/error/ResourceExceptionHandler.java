package com.teste.tecnicounisomaweb.controllers.error;

import com.teste.tecnicounisomaweb.services.errors.ParametrosInsuficientesError;
import com.teste.tecnicounisomaweb.services.errors.RecursoNaoEncontradoError;
import com.teste.tecnicounisomaweb.services.errors.RecursosExcedidos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

// Quando acontece um erro, esta classe decide como retornar
// a mensagem para o cliente
@ControllerAdvice // capacidade de gerenciar os erros que acontecem
public class ResourceExceptionHandler {
    @ExceptionHandler(RecursoNaoEncontradoError.class)
    public ResponseEntity<CustomErrorResponse> recursoNaoEncontradoError(RecursoNaoEncontradoError erro, HttpServletRequest request) {
        CustomErrorResponse response = new CustomErrorResponse();

        response.setTimestamp(LocalDateTime.now()); // Define a data e hora em que o erro ocorreu
        response.setStatus(HttpStatus.NOT_FOUND.value()); // Define como 404 o codigo de status
        response.setMessage(erro.getMessage()); // Define a mensagem de erro vinda do service
        response.setPath(request.getRequestURI()); // Define qual endpoint ocorreu a requisição

        // Retorna o objeto com os dados e código 404
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ParametrosInsuficientesError.class)
    public ResponseEntity<CustomErrorResponse> parametrosInsuficientesError(ParametrosInsuficientesError erro, HttpServletRequest request) {
        CustomErrorResponse response = new CustomErrorResponse();

        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(erro.getMessage());
        response.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


       @ExceptionHandler(RecursosExcedidos.class)
        public ResponseEntity<CustomErrorResponse> recursoExcedidoError(RecursosExcedidos erro, HttpServletRequest request){
            CustomErrorResponse response = new CustomErrorResponse();


            response.setTimestamp(LocalDateTime.now());
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.setMessage(erro.getMessage());
            response.setPath(request.getRequestURI());

           return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(response);

        }


}
