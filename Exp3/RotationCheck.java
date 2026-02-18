package Exp3;

import java.util.*;

public class RotationCheck {
    
    public static boolean RotateCheck(String sin,String sout) {
           char[] sina = sin.toLowerCase().toCharArray();
       char[] souta = sout.toLowerCase().toCharArray();
       boolean flag=false;
       for(char c1:sina){
            for(char c2:souta){
                if(c1==c2){
                  flag=true;
                }
                
            }
            if(flag==false){
                    return false;
                }
                flag=false;
       }
       return true;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the main string:");
        String sin = scanner.nextLine();
        System.out.println("Enter the string that you want to check if it is a rotation of the orignal string:");
        String sout = scanner.nextLine();
        
        if(sin.length()!=sout.length()){
            System.out.println("The lines are not equal so they cant be a rotation of each other.");
        }else{
            if(RotateCheck(sin, sout)){
                System.out.println("Yes,The string "+sout+" is a rotation of string "+sin+".");
            }else{
                 System.out.println("No,The string "+sout+" is not a rotation of string "+sin+".");
            }
        }
       
      
    }
}