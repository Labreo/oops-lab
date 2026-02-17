import java.util.Scanner;

public class exp1e {
   public static void leapyear(int n){
     if ((n % 4 == 0 && n % 100 != 0) || (n % 400 == 0)) {
        System.out.println("The number is a"+n+"year.");
     } else {
         System.out.println("The number is not a leap year.");
     }
     System.out.println();87
     
     int nextYear = n + 1;
     while (!((nextYear % 4 == 0 && nextYear % 100 != 0) || (nextYear % 400 == 0))) {
         nextYear++;
     }
     System.out.println("The next number which is a leap year is " + nextYear);
   } 
   public static void main(String[] args) {   
     Scanner scanner = new Scanner(System.in);
          System.out.print("Enter a number to check if it is a leap year: ");
        int n = scanner.nextInt();  
        scanner.close();
        leapyear(n);
 }
}
