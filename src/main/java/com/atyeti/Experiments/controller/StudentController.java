package com.atyeti.Experiments.controller;

import com.atyeti.Experiments.entity.Student;
import com.atyeti.Experiments.service.StudentService;
import com.atyeti.Experiments.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000") // allow React frontend
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @Autowired
    private StudentRepository repository;

    // ✅ Get all students
    @GetMapping
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    // ✅ Get student by ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return service.getBydId(id);
    }

    // ✅ Add a new student
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return service.addStudent(student);
    }
}