package com.atyeti.Experiments.dto;

import lombok.Data;

@Data
public class StudentDTO {
    private Long id;
    private String name;
    private String standard;

    public StudentDTO() {}

    public StudentDTO(Long id, String name, String standard) {
        this.id = id;
        this.name = name;
        this.standard = standard;
    }

    // getters and setters
}