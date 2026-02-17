import java.util.Scanner;

public class exp1d {
    public static void digitsum(int n){
        int sum=0;
     while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        System.out.println(sum);
    }
 public static void main(String[] args) {   
     Scanner scanner = new Scanner(System.in);
          System.out.print("Enter a number to add it's sum of all it's digits: ");
        int n = scanner.nextInt();  
        scanner.close();
        digitsum(n);
 }
}