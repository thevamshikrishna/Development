import java.util.Scanner;

public class StudentDatabaseApp {
    public static void main(String[] args) {

        // Ask how many users we want to add
        System.out.println("Enter Number of Students to enroll: ");
        Scanner sc=new Scanner(System.in);
        int numOfStudents=sc.nextInt();
        Student[] students=new Student[numOfStudents];

        // Create n number of students
        for(int i=0;i<numOfStudents;i++){
           // Student stu=new Student();
            students[i]=new Student();
            students[i].enroll();
            students[i].payTuition();
        }

        for(int i=0;i<numOfStudents;i++){
            System.out.println(students[i].toString());
        }
    }
}