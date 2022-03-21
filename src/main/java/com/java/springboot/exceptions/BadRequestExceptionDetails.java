package com.java.springboot.exceptions;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class BadRequestExceptionDetails  {
    private String title;
    private int status;
    private String details;
    private String developerMesaage;
    private LocalDateTime timeStamp;
}
