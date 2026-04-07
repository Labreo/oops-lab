package Exp7;
import java.util.Scanner;

class SmartCalculator {
    private String brand;

    public SmartCalculator() {
        this.brand = "Generic";
    }

    public SmartCalculator(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int add(int a, int b) {
        return a + b;
    }

    public int add(int a, int b, int c) {
        return a + b + c;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public double add(int a, double b) {
        return a + b;
    }

    @Override
    public String toString() {
        return "SmartCalculator [Brand=" + brand + "]";
    }
}

public class Q1_SmartCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Q1: Method Overloading ---");
        System.out.print("Enter calculator brand: ");
        String brand = scanner.nextLine();

        SmartCalculator calc = new SmartCalculator(brand);
        
        System.out.println("\nCalculator Info: " + calc.toString());

        System.out.print("Enter two integers: ");
        int int1 = scanner.nextInt();
        int int2 = scanner.nextInt();

        System.out.print("Enter a third integer: ");
        int int3 = scanner.nextInt();

        System.out.print("Enter two double values: ");
        double double1 = scanner.nextDouble();
        double double2 = scanner.nextDouble();

        System.out.println("\nResults:");
        System.out.println("Add two integers (" + int1 + ", " + int2 + "): " + calc.add(int1, int2));
        System.out.println("Add three integers (" + int1 + ", " + int2 + ", " + int3 + "): " + calc.add(int1, int2, int3));
        System.out.println("Add two doubles (" + double1 + ", " + double2 + "): " + calc.add(double1, double2));
        System.out.println("Add an integer and double (" + int1 + ", " + double1 + "): " + calc.add(int1, double1));

        scanner.close();
    }
}