import java.util.Scanner;

public class PrimeOrNot {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter a number : ");
        int num=sc.nextInt();

        if(isPrime(num)){
            System.out.println(num+" is Prime");
        }else{
            System.out.println();
        }
    }

    public static boolean isPrime(int n){
        if (n <= 1)
            return false;

        // Check from 2 to n-1
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;

        return true;
    }
}
