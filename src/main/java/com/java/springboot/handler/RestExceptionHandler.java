package com.java.springboot.handler;

import com.java.springboot.exceptions.BadRequestException;
import com.java.springboot.exceptions.BadRequestExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestExceptionDetails badRequestExceptionDetails) {
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request")
                        .details(badRequestExceptionDetails.getDeveloperMesaage())
                        .developerMesaage(badRequestExceptionDetails.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);

    }
}

/*
Caso tenhamos uma excecao do tipo Bad Request,
 ira utilizar o @ExceptionHandler(BadRequestException.class)
e ira retornar o valor do metodo
 */