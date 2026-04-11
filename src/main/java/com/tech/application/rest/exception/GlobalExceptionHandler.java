package com.tech.application.rest.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        // Registra el error globalmente
        logger.error("Se produjo una excepción: ", ex);

        // Devuelve una respuesta genérica al cliente
        return new ResponseEntity<>("Ocurrió un error interno en el servidor.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
