import java.util.Scanner;

public class q4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no to check that is neon or not:");
        int no = sc.nextInt();
        int temp=0;int result = 0;int saveno=no*no;
        
        while(saveno>0){
            temp=saveno%10;
            result = result + temp;
            saveno=saveno/10;
        }
            if(no==result){
        System.out.println("\nThe number is neon.");
    }else{
         System.out.println("\nThe number is not neon.");
    }
        sc.close();
    }

}
/*Neon Number: Check if the sum of the digits of the square of a number is equal to the
number itself (e.g., 9 \rightarrow 9^2 = 81 \rightarrow 8+1=9). */