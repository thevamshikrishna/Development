package com.atyeti.service;

import com.atyeti.dbConfig.DBConnection;
import com.atyeti.dbConfig.DbQueries;
import com.atyeti.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class StudentService {
    public static Logger logger=Logger.getLogger(StudentService.class.getName());
    public static void login(int id, String password) throws SQLException {
        Connection connection=DBConnection.connectDB();
        PreparedStatement statement= connection.prepareStatement(DbQueries.LOGIN);
        statement.setInt(1,id);
        statement.setString(2,password);

//        ResultSet rs = statement.executeQuery();
//        if (rs.next()) {
//            System.out.println("Welcome, " + rs.getString("name"));
//
//        } else {
//            System.out.println("Invalid ID or Password.");
//        }
        PreparedStatement loginStmt = connection.prepareStatement(
                "SELECT * FROM students WHERE id = ? AND password = ?"
        );

// Set login values (from user input)
        loginStmt.setInt(1, id);
        loginStmt.setString(2, password);

        ResultSet rs = loginStmt.executeQuery();

        if (rs.next()) {
            int studentId = rs.getInt("id");
            String name = rs.getString("name");
            String gmail = rs.getString("gmail");

            System.out.println("Login Successful!");
            System.out.println("Student ID: " + studentId);
            System.out.println("Name      : " + name);
            System.out.println("Email     : " + gmail);
            System.out.println("Subjects:");

            // Now fetch subject-wise details
            String detailsQuery = "SELECT subject, marks, grade, status FROM studentDetails WHERE student_id = ?";
            PreparedStatement detailsStmt = connection.prepareStatement(detailsQuery);
            detailsStmt.setInt(1, studentId);

            ResultSet detailsRs = detailsStmt.executeQuery();

            boolean hasSubjects = false;
            while (detailsRs.next()) {
                hasSubjects = true;
                System.out.println("  - " + detailsRs.getString("subject") +
                        " | Marks: " + detailsRs.getInt("marks") +
                        " | Grade: " + detailsRs.getString("grade") +
                        " | Status: " + detailsRs.getString("status"));
            }

            if (!hasSubjects) {
                System.out.println("  No subjects found for this student.");
            }

        } else {
            System.out.println("Invalid ID or Password.");
        }


    }

    public static void register(Student student) throws SQLException {

        Connection connection=DBConnection.connectDB();
        PreparedStatement statement= connection.prepareStatement(DbQueries.REGISTER);

       statement.setString(1,student.getStudentName());
       statement.setString(2,student.getGmail());
       statement.setString(3,student.getPassword());

        ResultSet rs = statement.executeQuery();

        if (rs.next()) { // Moves the cursor to the first row
            logger.info("Student Registered Successfully!");
            System.out.println("Student ID: " + rs.getInt("id")); // Ensure column name matches DB
        } else {
            logger.info("Failed to Register.");
        }

    }
}
