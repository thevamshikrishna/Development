package com.atyeti.Experiments.service;

import com.atyeti.Experiments.dto.StudentDTO;
import com.atyeti.Experiments.entity.Student;
import com.atyeti.Experiments.mapper.StudentMapper;
import com.atyeti.Experiments.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private StudentMapper mapper;

    public StudentDTO getById(Long id) {
        Student student = repository.findById(id).orElse(null);
        return (student == null) ? null : mapper.toDTO(student);
    }

    public StudentDTO addStudent(Student student) {
        Student saved = repository.save(student);
        return mapper.toDTO(saved);
    }
}