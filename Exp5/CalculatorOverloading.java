package Exp5;

import java.util.*;

class Calculator {
    public int a, b;
    public double c,d;

    public Calculator(int a, int b) {
        this.a = a;
        this.b = b;
    }
     public Calculator(double a, double b) {
        this.c = a;
        this.d = b;
        
    }

    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {

        return a + b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public static void displayMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {

            System.out.println(
                    "Press 0 if you want to add integers.Press 1 if you want to add double. Press 2 if you want to multiply integers. Press 3 if you want to multiply doubles.\nPress 4 if you want to exit.\n");
            int input = sc.nextInt();
            sc.nextLine();
        
            if (input == 0) {
                System.out.println("Enter the 1st Number:\n");
                int a = sc.nextInt();
                System.out.println("Enter the 2nd Number:\n");
                int b = sc.nextInt();
                Calculator cal = new Calculator(a, b);
                int sum = cal.add(a, b);
                System.out.println("The integer sum is "+sum+"\n");
                
            }
            if (input == 1) {
                System.out.println("Enter the 1st Number:\n");
                double a = sc.nextDouble();
                System.out.println("Enter the 2nd Number:\n");
                double b = sc.nextDouble();
                Calculator cal = new Calculator(a, b);
                 double sum = cal.add(a, b);
                System.out.println("The double sum is "+sum+"\n");
            }
             if (input == 2) {
                System.out.println("Enter the 1st Number:\n");
                int a = sc.nextInt();
                System.out.println("Enter the 2nd Number:\n");
                int b = sc.nextInt();
                Calculator cal = new Calculator(a, b);
                
                                 int sum = cal.multiply(a, b);
                System.out.println("The int multiply result is "+sum+"\n");
            }
            if (input == 3) {
                System.out.println("Enter the 1st Number:\n");
                double a = sc.nextDouble();
                System.out.println("Enter the 2nd Number:\n");
                double b = sc.nextDouble();
                Calculator cal = new Calculator(a, b);
                double sum = cal.multiply(a, b);
                    System.out.println("The double multiply result is "+sum+"\n");
            }
           
            if (input == 4) {
                break;}
            sc.nextLine();
             
        }
         sc.close();
    }
}

public class CalculatorOverloading {
    public static void main(String[] args) {
        Calculator.displayMenu();

    }
}