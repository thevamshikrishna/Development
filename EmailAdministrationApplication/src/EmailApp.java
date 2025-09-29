public class EmailApp {
    public static void main(String[] args) {

        Email em1=new Email("John","Smith");
        System.out.println(em1.showInfo());


//        Scanner sc=new Scanner(System.in);
//        System.out.println("Welcome to Atyeti Inc");
//        System.out.println("Select Options: \n 1. Register \n 2. Change Password \n 3. Display details");
//        int choice=sc.nextInt();
//
//        switch (choice) {
//            case 1:
//                Email.register();
//                break;
//            case 2:
//                Email.changePassword();
//                break;
//            case 3:
//                Email.displayDetails();
//                break;
//            default:
//                System.out.println("Thank you :) ");
//        }

    }
}