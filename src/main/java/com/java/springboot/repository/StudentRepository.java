package com.java.springboot.repository;

import com.java.springboot.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
    Passe a classe que representa o repositorio que nesse caso é Student e o atributo que representa o identificador unico que é o ID por tipo Long
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
