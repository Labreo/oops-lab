package Exp11;

import java.util.Scanner;
import java.util.Stack;

public class Q1_ReverseStack {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter the string you want to be reversed:");
        String s = sc.nextLine();
        System.out.println("\nThe orignal string is:"+s);
         Stack<Character> st = new Stack<>();
           System.out.println("\nThe reversed string is:");
  
        for(char c:s.toCharArray()){
            if(c==' '){
                st.push(c);
                
         while(st.empty()!=true){
            System.out.print(st.pop());
        }
            }else{
          st.push(c);}
          
        }
      
        
        sc.close();
    }
}
