package Exp10;

import java.util.Scanner;

class Calculator {
    private String brand;

    public Calculator() {
        this.brand = "Default Brand";
    }

    public Calculator(String brand) {
        this.brand = brand;
    }

    public void divide(int a, int b) {
        try {
            int result = a / b;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: Cannot divide by zero");
        } finally {
            System.out.println("Calculation attempted");
        }
    }

    @Override
    public String toString() {
        return "Calculator Details - Brand: " + this.brand;
    }
}

public class Q1_Calculator {
    private String sessionName;

    public Q1_Calculator() {
        this.sessionName = "Default Session";
    }

    public Q1_Calculator(String sessionName) {
        this.sessionName = sessionName;
    }

    @Override
    public String toString() {
        return "Application Session - Name: " + this.sessionName;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter session name: ");
        String session = scanner.nextLine();
        Q1_Calculator appSession = new Q1_Calculator(session);
        System.out.println(appSession.toString());

        System.out.print("Enter Calculator brand: ");
        String brand = scanner.nextLine();
        Calculator calc = new Calculator(brand);
        System.out.println(calc.toString());
        
        System.out.print("Enter two valid numbers for division: ");
        int a1 = scanner.nextInt();
        int b1 = scanner.nextInt();
        calc.divide(a1, b1);
        
        System.out.print("Enter a number and zero as divisor: ");
        int a2 = scanner.nextInt();
        int b2 = scanner.nextInt();
        calc.divide(a2, b2);
        
        System.out.print("Enter two negative numbers: ");
        int a3 = scanner.nextInt();
        int b3 = scanner.nextInt();
        calc.divide(a3, b3);
        
        scanner.close();
    }
}