import java.util.Scanner;

public class ReverseString {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter a String : ");
        String str=sc.nextLine();

        System.out.println(reverse(str));
    }

    public static String reverse(String str){
        StringBuilder sb=new StringBuilder(str).reverse();

        return sb.toString();
    }
}
