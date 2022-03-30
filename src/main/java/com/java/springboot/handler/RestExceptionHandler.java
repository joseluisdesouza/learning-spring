package com.java.springboot.handler;

import com.java.springboot.exceptions.BadRequestException;
import com.java.springboot.exceptions.BadRequestExceptionDetails;
import com.java.springboot.exceptions.ExceptionDetails;
import com.java.springboot.exceptions.ValidationExceptionDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(
            BadRequestExceptionDetails badRequestExceptionDetails) {
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request")
                        .details(badRequestExceptionDetails.getDeveloperMessage())
                        .developerMessage(badRequestExceptionDetails.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);

    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ValidationExceptionDetails> handlerMethodArgumentNotValidException(
//            MethodArgumentNotValidException methodArgumentNotValidException) {
//        List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
//        var fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
//        var fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
//
//        return new ResponseEntity<>(
//                ValidationExceptionDetails.builder()
//                        .timeStamp(LocalDateTime.now())
//                        .status(HttpStatus.BAD_REQUEST.value())
//                        .title("Bad Request Exception, Invalid Fields")
//                        .details(methodArgumentNotValidException.getMessage())
//                        .developerMessage(methodArgumentNotValidException.getClass().getName())
//                        .fields(fields)
//                        .fieldsMessage(fieldsMessage)
//                        .build(), HttpStatus.BAD_REQUEST);
//
//    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Bad Request Exception, Invalid Fields")
                .details(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .build();

        return new ResponseEntity<>(exceptionDetails, headers, status);
    }
}
/*
    Handler é uma forma de interceptar exceções e adicionar o que foi defido nesta classe
 */

/*
    BindingResult
        [ BindingResult] é o objeto do Spring que contém o resultado da validação e vinculação e contém erros que podem ter ocorrido.
         O BindingResultdeve vir logo após o objeto de modelo que é validado, caso contrário, o Spring falhará ao validar o objeto e lançará uma exceção.

        Quando o Spring vê @Valid, ele tenta encontrar o validador para o objeto que está sendo validado.
        O Spring pega automaticamente as anotações de validação se você tiver "orientado por anotação" ativado.
        O Spring então invoca o validador e coloca quaisquer erros no BindingResulte adiciona o BindingResult ao modelo de visualização.

        Basicamente BindingResulté uma interface que dita como o objeto que armazena o resultado da validação deve armazenar e
        recuperar o resultado da validação (erros, tentativa de vincular a campos não permitidos etc)
 */

/*
    Caso tenhamos uma excecao do tipo Bad Request,
    ira utilizar o @ExceptionHandler(BadRequestException.class)
    e ira retornar o valor do metodo
 */