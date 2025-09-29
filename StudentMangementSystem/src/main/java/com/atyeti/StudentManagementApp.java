package com.atyeti;

import com.atyeti.model.Student;
import com.atyeti.service.StudentService;
import com.atyeti.service.TeacherService;

import java.sql.SQLException;
import java.util.Scanner;

public class StudentManagementApp {
    public static void main(String[] args) throws SQLException {
        System.out.println("Welcome to School Management! \n1.Login as Student \n2.Login as Teacher/Admin \n3.Registration \n Choose any one of Above!");
        Scanner sc=new Scanner(System.in);
        int option=sc.nextInt();


        switch(option){
            case 1:
                System.out.println("Enter UserId: ");
                int id=sc.nextInt();
                System.out.println("Enter Password: ");
                String password=sc.nextLine();
                password=sc.nextLine();
                StudentService.login(id,password);
                break;

            case 2:
                System.out.println("Enter registered Number: ");
                int phNumber=sc.nextInt();
                System.out.println("Enter Password: ");
                String passwordAdmin=sc.nextLine();
                passwordAdmin=sc.nextLine();
                TeacherService.teacherOrAdminLogin(phNumber,passwordAdmin);
                break;
            case 3:
                System.out.println("Enter Name: ");
                String userName=sc.nextLine();
                userName=sc.nextLine();
                System.out.println("Enter Gmail: ");
                String gmail=sc.nextLine();
                System.out.println("Create a PassWord: ");
                String newPassword=sc.nextLine();
                Student obj=new Student(userName,gmail,newPassword);
                StudentService.register(obj);

        }
    }
}