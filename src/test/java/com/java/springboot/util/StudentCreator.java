package com.java.springboot.util;

import com.java.springboot.domain.Student;

public class StudentCreator {

    public static Student createdStudentToBeSaved() {
        return Student.builder()
                .name("Spider")
                .build();
    }

    public static Student createdValidStudent() {
        return Student.builder()
                .id(1L)
                .name("Homem-aranha")
                .build();
    }

    public static Student createdValidUpdatedStudent() {
        return Student.builder()
                .id(1L)
                .name("Spider 2")
                .build();
    }
}
