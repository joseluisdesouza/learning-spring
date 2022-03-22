package com.java.springboot.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class StudentRequest {
    @NotBlank(message = "The student name cannot be empty")
    @NotNull(message = "The student name cannot be null")
    private String name;
}
