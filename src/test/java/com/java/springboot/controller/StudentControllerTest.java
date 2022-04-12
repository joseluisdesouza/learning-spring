package com.java.springboot.controller;

import com.java.springboot.domain.Student;
import com.java.springboot.service.StudentService;
import com.java.springboot.util.StudentCreator;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.java.springboot.util.StudentCreator.createdValidStudent;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class StudentControllerTest {

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentService studentServiceMock;

    @BeforeEach
    void setUp() {
        PageImpl<Student> studentPage = new PageImpl<>(List.of(createdValidStudent()));
        when(studentServiceMock.findAllPaginated(any()))
                .thenReturn(studentPage);
    }

    @Test
    void save() {
    }

    @Test
    @DisplayName("Return all students with pagination")
    void findAllPaginated() {
    }

    @Test
    void findAll() {
    }

    @Test
    void filterName() {
    }

    @Test
    void findById() {
    }

    @Test
    void delete() {
    }

    @Test
    void replace() {
    }
}