package com.atyeti.model;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    public static int Id;
    private String studentName;
    private String gmail;
    private String password;
}
