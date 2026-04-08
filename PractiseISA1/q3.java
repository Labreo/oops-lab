import java.util.Scanner;

public class q3{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String binary;
        int result=0;
        System.err.println("Enter the binary string:");
        binary = sc.nextLine();
        for(int i = binary.length()-1;i>0;--i){
            if(binary.charAt(i)=='1'){
                result=result+(int)Math.pow(2,i);
            }
        }
        System.err.println("The result is "+result);
        sc.close();
    }
}