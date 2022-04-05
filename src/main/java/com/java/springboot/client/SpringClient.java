package com.java.springboot.client;

import com.java.springboot.domain.Student;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        //retorna por id
        ResponseEntity<Student> forEntity = new RestTemplate()
                .getForEntity("http://localhost:8080/students/{id}",
                        Student.class, 2);
        log.info(forEntity);

        //retorna por array
        Student[] students = new RestTemplate()
                .getForObject("http://localhost:8080/students/all",
                        Student[].class);
        log.info(Arrays.toString(students));

        ResponseEntity<List<Student>> studentsToList = new RestTemplate()
                .exchange("http://localhost:8080/students/all",
                        HttpMethod.GET,
                        null
                        , new ParameterizedTypeReference<>() {
                        });
        log.info(studentsToList.getBody());

        //cria student por postForObject
        Student student = Student.builder().name("Muriel").build();
        Student studentCreated = new RestTemplate()
                .postForObject("http://localhost:8080/students",
                        student, Student.class);
        log.info("Student created {} ", studentCreated);

        //cria student por exchange
        Student student2 = Student.builder().name("Duvan Zapata").build();
        ResponseEntity<Student> exchange = new RestTemplate()
                .exchange("http://localhost:8080/students",
                        HttpMethod.POST,
                        new HttpEntity<>(student2, createJsonHeader()),
                        Student.class);
        log.info("Student created {} ", exchange);

        Student studentToBeUpdate = exchange.getBody();
        studentToBeUpdate.setName("Atalanta");

        ResponseEntity<Void> exchangeUpdate = new RestTemplate()
                .exchange("http://localhost:8080/students",
                        HttpMethod.PUT,
                        null,
                        Void.class);

        log.info("Student update {} ", exchangeUpdate);

        ResponseEntity<Void> exchangeDelete = new RestTemplate()
                .exchange("http://localhost:8080/students/{id}",
                        HttpMethod.DELETE,
                        new HttpEntity<>(studentToBeUpdate, createJsonHeader()),
                        Void.class, studentToBeUpdate.getId());
        //CTRL + P mostra opções de parametros

        log.info("Student deleted {} ", exchangeDelete);
    }

    //retorna o header junto a requisição
    private static HttpHeaders createJsonHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }

}
