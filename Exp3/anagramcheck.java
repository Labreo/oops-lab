package Exp3;


import java.util.Scanner;


public class Anagramcheck {
    public static boolean anagram(String s1,String s2) {
        boolean flag = false;
        for(char c1:s1.toCharArray()){
         for(char c2:s2.toCharArray()){
            if(c1==c2){
             flag=true;
            }
         } 
         if(flag == false){
            return false;
         }
         flag = false;
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the first string:");
        String s1 = scanner.nextLine();
        s1=s1.toLowerCase();
           System.out.println("Enter the second string:");
        String s2 = scanner.nextLine();  
        s2=s2.toLowerCase();
        if(s1.length()!=s2.length()){
            System.out.println("The strings are not the same length so they cannot be anagrams.");
        }else{
            if(anagram(s1, s2)){
                System.out.println("They are anagrams");
            }else{
                System.out.println("They are not anagrams");
            }
        }
    }
}
