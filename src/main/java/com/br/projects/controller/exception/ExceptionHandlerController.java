package com.br.projects.controller.exception;

import com.br.projects.service.exception.NotAuthorizedActionException;
import com.br.projects.service.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        StandardError erro = new StandardError();
        erro.setTimeStamp(Instant.now());
        erro.setStatus(HttpStatus.NOT_FOUND.value());
        erro.setError("Resource not found");
        erro.setMessage(e.getMessage());
        erro.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(NotAuthorizedActionException.class)
    public ResponseEntity<StandardError> resourceNotFound(NotAuthorizedActionException e, HttpServletRequest request) {
        StandardError erro = new StandardError();
        erro.setTimeStamp(Instant.now());
        erro.setStatus(HttpStatus.UNAUTHORIZED.value());
        erro.setError("Not Authorized Action");
        erro.setMessage(e.getMessage());
        erro.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(erro);
    }
}
