package com.atyeti.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeacherOrAdmin {
    String phoneNumberAsId;
    String password;
}