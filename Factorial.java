import java.util.Scanner;

public class Factorial {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter a number : ");
        int num=sc.nextInt();

        System.out.println(fibanaci(num));
    }

    public static int fibanaci(int n){
        int fact=1;
        for(int i=1;i<=n;i++){
            fact=fact*i;
        }

        return fact;
    }
}
