package PractiseISA1;
import java.util.*;


public class Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter binary string: ");
        String binary = sc.nextLine();
        
        int total = 0;
        int len = binary.length();
        
        for (int i = 0; i < len; i++) {
            // Process bits from right to left (len - 1 - i)
            if (binary.charAt(i) == '1') {
                total += (int) Math.pow(2, i);
            }
        }
        
        System.out.println("Decimal value: " + total);
        sc.close();
    }
}
