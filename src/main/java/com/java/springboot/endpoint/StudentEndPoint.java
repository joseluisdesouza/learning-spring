package com.java.springboot.endpoint;

import com.java.springboot.domain.Student;
import com.java.springboot.error.CustomErrorType;
import com.java.springboot.util.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("students")
@Log4j2
public class StudentEndPoint {
    //localhost:8080/students
    @Autowired
    private DateUtil dateUtil;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Student student) {
        Student.studentList.add(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listAll() {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(Student.studentList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") int id) {
        Student student = new Student();
        student.setId(id);
        int index = Student.studentList.indexOf(student);
        if (index == -1)
            return new ResponseEntity<>(new CustomErrorType("Student not found"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(Student.studentList.get(index), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestBody Student student) {
        Student.studentList.remove(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

/**
 * endpoint é o nosso final aonde os usuarios vão acessar a nossa API normalmente
 * por uma URL
 * API(Application Programming Interface)
 * <p>
 * a diferença da @RestController para a @Controller é que a primeira
 * adiciona automaticamente o @ResponseBory para todos os seus metodos
 *
 * @ResponseBody significa que quero mandar um JSON simples no retorno da resposta
 * @RequestMapping é o caminho para eu chegar nesse endpoint
 * <p>
 * index.of para retornar o valor do indice
 */