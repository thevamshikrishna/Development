import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class Email {

    private String firstName;
    private String lastName;
    private String password;
    private String department;
    private String email;
    private int mailboxCapacity=500;
    private int defaultPasswordLength=8;
    private String alternateEmail;
    private String companySuffix="atyeti.com";


    // Constructor to receive the first and last name;

    public Email(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

        // Call a method asking for the department
        this.department=setDepartment();

        //call a method that returns a random password
        this.password=randomPassword(defaultPasswordLength);
        // Combine elements to generate email
        email=firstName.toLowerCase()+"."+lastName.toLowerCase()+"@"+department+"."+companySuffix;

    }


    // Ask for the department
    private String setDepartment(){
        System.out.println("New worker: "+firstName+". DEPARTMENT CODES\n1 for Sales \n2 for Development\n3 for Accounting\n0 for none\nEnter department code: ");
        Scanner in=new Scanner(System.in);
        int depChoice=in.nextInt();

        if(depChoice==1){return "sales";}
        if(depChoice==2){return "development";}
        if(depChoice==3){return "accounting";}
        else{ return "";}

    }

    // Generate a random password

    private String randomPassword(int length){
        String passwordSet="ABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%";
        char[] password=new char[length];
        for(int i=0;i<length;i++){
            int random=(int) (Math.random()* passwordSet.length());
            password[i]=passwordSet.charAt(random);
        }
        System.out.println("YOUR PASSWORD: "+password);
        return new String(password);
    }

    // Set the mailbox capacity
    public void setMailMailCapacity(int capacity){
        this.mailboxCapacity=capacity;
    }

    // Set the alternate email
    public void setAlternateEmail(String altEmail){
        this.alternateEmail=altEmail;
    }

    //Change the password
    public void changePassword(String password){
        this.password=password;
    }


    public int getMailboxCapacity(){return mailboxCapacity;}
    public String getAlternateEmail(){ return alternateEmail;}
    public String getPassword(){return password;}


    public String showInfo(){
        return "DISPLAY NAME: "+firstName+" "+lastName+
                "\nCOMPANY EMAIL: "+email+
                "\nMAILBOX CAPACITY: "+mailboxCapacity+"mb";
    }



//    static HashMap<String, String> login = new HashMap<>();
//    static HashMap<String, String> display = new HashMap<>();
//    static Scanner sc = new Scanner(System.in);
//
//    public static void register() {
//        sc.nextLine();
//        System.out.print("Enter your First Name: ");
//        String firstName = sc.nextLine();
//
//        System.out.print("Enter your Last Name: ");
//        String lastName = sc.nextLine();
//
//        System.out.print("Enter your Department: ");
//        String department = sc.nextLine();
//
//       // String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@atyeti.com";
//        String email = firstName.trim().toLowerCase() + "." + lastName.trim().toLowerCase() + "@atyeti.com";
//
//
//        String password = generatePassword(15);
//        System.out.println("Generated Password: " + password);
//
//        login.put(email, password);
//
//        String profile = "First Name: " + firstName + "\nLast Name: " + lastName +
//                "\nDepartment: " + department;
//        display.put(email, profile);
//
//        System.out.println("Your email has been created: " + email);
//    }
//
//    public static void changePassword() {
//        sc.nextLine();
//        System.out.print("Enter your Email: ");
//        String email = sc.nextLine();
//
//        if (login.containsKey(email)) {
//            System.out.print("Enter your Current Password: ");
//            String currentPassword = sc.nextLine();
//
//            if (currentPassword.equals(login.get(email))) {
//                System.out.print("Enter your New Password: ");
//                String newPassword = sc.nextLine();
//
//                login.put(email, newPassword);
//                System.out.println("Password successfully changed.");
//            } else {
//                System.out.println("Incorrect current password.");
//            }
//        } else {
//            System.out.println("Email not found.");
//        }
//    }
//
//    public static void displayDetails() {
//        sc.nextLine();
//        System.out.print("Enter your Work Email: ");
//        String email = sc.nextLine().trim().toLowerCase();
//
//        if (login.containsKey(email)) {
//            System.out.print("Enter your Password: ");
//            String password = sc.nextLine();
//
//            if (password.equals(login.get(email))) {
//                System.out.println("Login Successful!");
//                System.out.println("Profile Details:");
//                System.out.println(display.get(email));
//            } else {
//                System.out.println("Incorrect Password!");
//            }
//        } else {
//            System.out.println("Email not found.");
//        }
//    }
//
//
//    private static String generatePassword(int length) {
//        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?";
//        SecureRandom random = new SecureRandom();
//        StringBuilder password = new StringBuilder(length);
//
//        for (int i = 0; i < length; i++) {
//            int index = random.nextInt(characters.length());
//            password.append(characters.charAt(index));
//        }
//
//        return password.toString();
//    }

}
