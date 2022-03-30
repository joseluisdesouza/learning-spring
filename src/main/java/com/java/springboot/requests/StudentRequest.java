package com.java.springboot.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {
//    @NotEmpty(message = "The student name cannot be empty")
    private String name;
}
