
package Exp3;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ValidateEmail {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the email you want to validate:");
        String email = scanner.nextLine();
        String emailregex ="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
     Pattern p = Pattern.compile(emailregex);
     if(p.matcher(email).matches()){
        System.out.println("The email:"+email+" is a valid email.");
     }else{
         System.out.println("The email:"+email+" is not a valid email.");
     }
    }
    

}
