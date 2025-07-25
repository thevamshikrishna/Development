import java.util.Scanner;

public class Student {

    private String firstName;
    private String lastName;
    private int gradeYear;
    private String studentId;
    private String courses="";
    private int tuitionBalance=0;
    private static int costOfCourse=600;
    private static int id=1000;



    // Constructor: prompt user to enter student's name and year

    public Student() {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter student first name: ");
        this.firstName=sc.nextLine();

        System.out.println("Enter student last name: ");
        this.lastName = sc.nextLine();

        System.out.println("1 - Freshermen\n2 - Sophmore\n3 - Junior\n4 - Senior\n Enter student class level: ");
        this.gradeYear=sc.nextInt();

        setStudentId();

    }

    // Generate a ID
    private void setStudentId(){
        // Grade Level + ID
        id++;
        this.studentId= gradeYear+""+id;

    }

    // Enroll in courses
    public void enroll(){
        // get inside a loop, user hits 0

        do {
            System.out.print("Enter Course to enroll (Q to quit): ");
            Scanner sc = new Scanner(System.in);
            String course = sc.nextLine();

            if (!course.equals("Q")) {
                courses = courses + "\n " + course;
                tuitionBalance = tuitionBalance + costOfCourse;
            }
            else{break;}
        }while(1!=0);

    }

    // View Balance
    public void viewBalance(){
        System.out.println("YOUR BALANCE IS: $"+tuitionBalance);
    }

    // Pay Tuition
    public void payTuition(){
        viewBalance();
        System.out.println("Enter your payment :$");
        Scanner sc=new Scanner(System.in);
        int payment=sc.nextInt();
        tuitionBalance=tuitionBalance-payment;
        System.out.println("Thank you for your payment of $"+payment);
        viewBalance();
    }

    // Show status
  //  public String showInfo(){
    public String toString(){
        return "Name: "+firstName+" "+lastName+
                "\nGrade Level: "+gradeYear+
                "\nStudent Id: "+studentId+
                "\nCourses Enrolled:"+courses+
                "\nBalance: $"+tuitionBalance;
    }
}
