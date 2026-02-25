
package Exp3;

import java.util.*;

public class StringToTitleCase {
    public static void main(String[] args) {
  Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the string which you want to convert into a titleCase:");
              
        String s1 = scanner.nextLine().toLowerCase();
        String[] parts = s1.split(" ");

        StringBuilder result = new StringBuilder();
        for(String p:parts){
            result = result.append(Character.toTitleCase(p.charAt(0))).append(p.substring(1)).append(" ");
        }
        System.out.println(result.toString().trim());
    }
}
