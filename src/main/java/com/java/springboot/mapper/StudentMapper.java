package com.java.springboot.mapper;

import com.java.springboot.domain.Student;
import com.java.springboot.requests.StudentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class StudentMapper {
    public static final StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
    public abstract Student toStudent(StudentRequest studentRequest);
}
