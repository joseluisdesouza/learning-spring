package com.java.springboot.exceptions;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ValidationExceptionDetails extends ExceptionDetails {
    private final String fields;
    private final String fieldsMessage;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ValidationExceptionDetails that = (ValidationExceptionDetails) o;

        if (fields != null ? !fields.equals(that.fields) : that.fields != null) return false;
        return fieldsMessage != null ? fieldsMessage.equals(that.fieldsMessage) : that.fieldsMessage == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (fields != null ? fields.hashCode() : 0);
        result = 31 * result + (fieldsMessage != null ? fieldsMessage.hashCode() : 0);
        return result;
    }
}
