package com.atyeti.dbConfig;

public interface DbQueries {
    String LOGIN = "SELECT * FROM students WHERE id = ? AND password = ?";
    String REGISTER="INSERT INTO students (name, gmail, password) VALUES (?, ?, ?)";
}
