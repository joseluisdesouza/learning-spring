package com.java.springboot.exceptions;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
// @SuperBuilder é como se usasse o Builder da classe que foi estendida (ExceptionDetails)
@SuperBuilder
public class BadRequestExceptionDetails extends ExceptionDetails {
}
