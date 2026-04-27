import java.util.Scanner;

public class CheckPalindrome {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter a number : ");
        String str=sc.nextLine();

        if(isPolindrome(str)){
            System.out.println(str+" is a polindrome");
        }else{
            System.out.println(str+" is not a polindrome");
        }
    }

    private static boolean isPolindrome(String str) {
        StringBuilder sb=new StringBuilder(str).reverse();

        return sb.toString().equals(str);
    }
}
