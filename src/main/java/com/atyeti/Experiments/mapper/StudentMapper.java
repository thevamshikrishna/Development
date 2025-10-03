package com.atyeti.Experiments.mapper;

import com.atyeti.Experiments.dto.StudentDTO;
import com.atyeti.Experiments.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    // entity → dto
    @Mapping(source = "studentName", target = "name")
    StudentDTO toDTO(Student student);

    // dto → entity
    @Mapping(source = "name", target = "studentName")
    Student toEntity(StudentDTO dto);
}