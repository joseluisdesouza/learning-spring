package com.java.springboot.repository;

import com.java.springboot.domain.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for Student Repository")
class StudentRepositoryTest {

    @Mock
    private StudentRepository studentRepository;

    @Test
    @DisplayName("Save student when successful")
    void save_Student_WhenSuccessful() {
        Student studentToBeSaved = createdStudent();
        Student studentSaved = this.studentRepository.save(studentToBeSaved);

        Assertions.assertThat(studentSaved).isNotNull();
        Assertions.assertThat(studentSaved.getId()).isNotNull();
        Assertions.assertThat(studentSaved.getName()).isEqualTo(studentToBeSaved.getName());
    }

    @Test
    @DisplayName("Save update student when successful")
    void save_UpdateStudent_WhenSuccessful() {
        Student studentToBeSaved = createdStudent();
        Student studentSaved = this.studentRepository.save(studentToBeSaved);

        studentSaved.setName("Venom");
        Student studentUpdated = this.studentRepository.save(studentToBeSaved);

        Assertions.assertThat(studentUpdated).isNotNull();
        Assertions.assertThat(studentUpdated.getId()).isNotNull();
        Assertions.assertThat(studentUpdated.getName()).isEqualTo(studentSaved.getName());
    }

    @Test
    @DisplayName("Delete student when successful")
    void deleted_DeleteStudent_WhenSuccessful() {
        Student studentToBeSaved = createdStudent();
        Student studentSaved = this.studentRepository.save(studentToBeSaved);

        this.studentRepository.delete(studentSaved);

        Optional<Student> studentOptional = this.studentRepository.findById(studentSaved.getId());
        Assertions.assertThat(studentOptional).isNotPresent();
    }

    @Test
    @DisplayName("Find by name return list student when successful")
    void findByName_Student_WhenSuccessful() {
        Student studentToBeSaved = createdStudent();
        Student studentSaved = this.studentRepository.save(studentToBeSaved);

        String studentSavedName = studentSaved.getName();

        List<Student> studentList = this.studentRepository.findByName(studentSavedName);

        Assertions.assertThat(studentList).isNotNull();
        Assertions.assertThat(studentList).isEmpty();
        Assertions.assertThat(studentList).contains(studentSaved);
    }

    private Student createdStudent() {
        return Student.builder()
                .name("Homem-aranha")
                .build();
    }
}