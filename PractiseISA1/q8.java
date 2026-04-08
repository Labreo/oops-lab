import java.util.Scanner;

public class q8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no for which the sum has to be found:");
        int no=sc.nextInt();
        int result=0;
        while(no>0){
            result = no%10 + result;
            no=no/10;
        }
      System.out.println("The result is "+result);
        sc.close();
    }
}
