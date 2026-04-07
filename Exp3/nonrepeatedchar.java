package Exp3;

import java.util.Scanner;

public class Nonrepeatedchar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the string:");
        String str = sc.nextLine().toLowerCase();

        for (int i = 0; i < str.length(); i++) {
            int count = 0;
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    count++;
                }
            }
            if (count == 1) {
                System.out.println("The first non repeated char is " + str.charAt(i));
                return;
            }
        }

        System.out.println("No non-repeated character found.");
    }
}
