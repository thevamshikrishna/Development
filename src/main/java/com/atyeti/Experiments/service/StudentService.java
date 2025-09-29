package com.atyeti.Experiments.service;

import com.atyeti.Experiments.entity.Student;
import com.atyeti.Experiments.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public Student getBydId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Student addStudent(Student student) {

        Student save = repository.save(student);
        return save;
    }
}