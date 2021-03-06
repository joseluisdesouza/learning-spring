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
    Handler ?? uma forma de interceptar exce????es e adicionar o que foi defido nesta classe
 */

/*
    BindingResult
        [ BindingResult] ?? o objeto do Spring que cont??m o resultado da valida????o e vincula????o e cont??m erros que podem ter ocorrido.
         O BindingResultdeve vir logo ap??s o objeto de modelo que ?? validado, caso contr??rio, o Spring falhar?? ao validar o objeto e lan??ar?? uma exce????o.

        Quando o Spring v?? @Valid, ele tenta encontrar o validador para o objeto que est?? sendo validado.
        O Spring pega automaticamente as anota????es de valida????o se voc?? tiver "orientado por anota????o" ativado.
        O Spring ent??o invoca o validador e coloca quaisquer erros no BindingResulte adiciona o BindingResult ao modelo de visualiza????o.

        Basicamente BindingResult?? uma interface que dita como o objeto que armazena o resultado da valida????o deve armazenar e
        recuperar o resultado da valida????o (erros, tentativa de vincular a campos n??o permitidos etc)
 */

/*
    Caso tenhamos uma excecao do tipo Bad Request,
    ira utilizar o @ExceptionHandler(BadRequestException.class)
    e ira retornar o valor do metodo
 */