
import java.util.*;
import java.util.regex.Pattern;

public class Q11 {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter the no of elements in array:");
        int n = sc.nextInt();
        int k=0;
        int[] m=new int[n];
        for(int i=0;i<n;++i){
            System.out.println("\nEnter the element in index "+i+":");
            k = sc.nextInt();
            m[i]=k;
        }
           System.out.println("\nEnter which element to search for in the array:");
          int z = sc.nextInt();
     for(int i=0;i<n;++i){
        if(z==m[i]){
            System.out.println("\nThe element can be found at "+i+".");
        }
     }
     int[][] l= new int[5][3];
    
        
        sc.close();
    }
}
