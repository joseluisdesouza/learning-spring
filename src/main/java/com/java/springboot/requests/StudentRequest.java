package com.java.springboot.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class StudentRequest {
    @NotEmpty(message = "The student name cannot be empty")
    private String name;
}
