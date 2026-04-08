package Exp3;

import java.util.*;

public class RotationCheck {

    public static boolean RotateCheck(String sin, String sout) {
        if (sin.length() != sout.length() || sin.isEmpty()) {
            return false;
        }
        String combined = sin.toLowerCase() + sin.toLowerCase();
        return combined.contains(sout.toLowerCase());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the main string:");
        String sin = scanner.nextLine();
        System.out.println("Enter the string that you want to check if it is a rotation of the orignal string:");
        String sout = scanner.nextLine();

        if (RotateCheck(sin, sout)) {
            System.out.println("Yes,The string " + sout + " is a rotation of string " + sin + ".");
        }else {
            System.out.println("No,The string " + sout + " is not a rotation of string " + sin + ".");
        }
        scanner.close();
    }
}
