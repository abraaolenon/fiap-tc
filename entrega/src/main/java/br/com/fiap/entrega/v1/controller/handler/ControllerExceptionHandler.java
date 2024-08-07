package br.com.fiap.entrega.v1.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> entityNotFoundException(EntityNotFoundException e) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> entityNotFoundException(IllegalArgumentException e) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

    }
}
