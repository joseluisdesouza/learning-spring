package com.java.springboot.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomErrorType {
    private String errorMessage;
}
