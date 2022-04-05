package com.java.springboot.controller;

import com.java.springboot.domain.Student;
import com.java.springboot.requests.StudentRequest;
import com.java.springboot.service.StudentService;
import com.java.springboot.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Logica de negocio não vão nas controllers é uma má pratica, coloque sempre na service
 *
 * @RequestParam : é utilizado para pegar uma parâmetro de query da url
 * @PathVariable : Já a anotação @PathVariable serve para pegar um trecho da url que geralmente é dinâmico
 * Por padrão as duas anotações são required default = true, ou seja, é obrigatorio, para resolver isso caso não queira que seja obrigatorio
 * coloque assim na passagem de parametro required=false
 *
 * Refatoração de StudentEndPoint para controller, se fosse mais de uma tela usariamos resource
 */

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("students")
public class StudentController {
    //localhost:8080/students
    @Autowired
    private DateUtil dateUtil;

    private final StudentService studentService;

    @PostMapping
    public Student save(@RequestBody @Valid StudentRequest studentRequest) {
        return studentService.save(studentRequest);
    }

    @GetMapping
    public ResponseEntity<Page<Student>> findAllPaginated(Pageable pageable) {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        log.info("here");
        return new ResponseEntity<>(studentService.findAllPaginated(pageable), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> findAll() {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        log.info("here");
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public List<Student> filterName(@RequestParam String name) {
        return studentService.findByNameFilter(name);
    }

    @GetMapping(path = "/{id}")
    public Student findById(@PathVariable Long id) {
        return studentService.findByIdOrThrowNotFoundException(id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> replace(@RequestBody Student student) {
        studentService.replace(student);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
 * <p>
 * OBS: irei manter esse padrão antigo de definir os paths ex: @RequestMapping(method = RequestMethod.DELETE) pois podemos  usar somente, @DeleteMapping
 * iria manter mas esse padrão @RequestMapping estava me imcomodando
 * @ResponseEntity representa toda a resposta HTTP: código de status, cabeçalhos e corpo .
 * Como resultado, podemos usá-lo para configurar totalmente a resposta HTTP.
 */