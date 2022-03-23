package com.java.springboot.service;

import com.java.springboot.domain.Student;
import com.java.springboot.exceptions.BadRequestException;
import com.java.springboot.mapper.StudentMapper;
import com.java.springboot.repository.StudentRepository;
import com.java.springboot.requests.StudentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

            SOBRE TRANSAÇÕES NO METODO SAVE: em caso de exceção eu preciso que de um rollback
            ou seja, lanca a exceção mas não salva nenhum estudante e para isso usamos a
            annotation @Transaction e é claro precisamos verificar se as tabelas são do tipo
            InnoDB no engine (show table status)

            O @Transaction não da rollback para exceções do tipo checked, para esse problema:
            @Transaction(rollbackFor = Exception.class)
     */
    @Transactional
    public Student save(StudentRequest studentRequest) {
        //usando o mapper de student
        return studentRepository.save(StudentMapper.INSTANCE.toStudent(studentRequest));
    }

    public Student findByIdOrThrowNotFoundException(Long id) {
        return studentRepository.findById(id).
                orElseThrow(() -> new BadRequestException("Student not found"));
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public List<Student> findByNameFilter(String name) {
        return studentRepository.findByName(name);
    }

    public void delete(Long id) {
//        Student.studentList.remove(id);
        studentRepository.deleteById(id);
    }

    public void replace(Student student) {
        delete(student.getId());
        studentRepository.save(student);
    }
}
