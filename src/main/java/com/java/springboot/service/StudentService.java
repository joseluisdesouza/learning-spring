package com.java.springboot.service;

import com.java.springboot.domain.Student;
import com.java.springboot.mapper.StudentMapper;
import com.java.springboot.repository.StudentRepository;
import com.java.springboot.requests.StudentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    /*
        É possivel usar do padrão builder porem se a classe tiver muitos atributos sera inviavel
        EX:
            Student.builder()
                .name(studentRequest.getName())
            .build();
            Extraia para uma variavel e coloque no retorno!
        Usei de inicio esse padrão:

            public Student save(StudentRequest studentRequest) {
                var student = new Student();
                BeanUtils.copyProperties(studentRequest, student);
                return studentRepository.save(student);
            }
     */
    public Student save(StudentRequest studentRequest) {
        //usando o mapper de student
        return studentRepository.save(StudentMapper.INSTANCE.toStudent(studentRequest));
    }

    public Student findByIdOrThrowNotFoundException(Long id) {
        return studentRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public List<Student> findByNameFilter(String name) {
        return studentRepository.findByName(name);
    }

    public void delete(Long id) {
        Student.studentList.remove(id);
        studentRepository.deleteById(id);
    }

    public void replace(Student student) {
        delete(student.getId());
        studentRepository.save(student);
    }
}
