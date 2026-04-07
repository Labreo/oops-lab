package Exp8;
import java.util.Scanner;

abstract class Vehicle {
    protected String brand;

    public Vehicle(String brand) {
        this.brand = brand;
        System.out.println("[Vehicle Constructor] Initialized brand: " + this.brand);
    }

    public abstract void start();

    public void display() {
        System.out.println("Vehicle Brand: " + brand);
    }
}

class Car extends Vehicle {
    public Car(String brand) {
        super(brand); 
    }

    @Override
    public void start() {
        System.out.println("The " + brand + " car starts with a push button.");
    }
}

class Bike extends Vehicle {
    public Bike(String brand) {
        super(brand); 
    }

    @Override
    public void start() {
        System.out.println("The " + brand + " bike starts with a kick or self-start.");
    }
}

public class Q1_VehicleSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Q1: Vehicle System ---");
        System.out.print("Enter Car brand: ");
        String carBrand = scanner.nextLine();
        
        System.out.print("Enter Bike brand: ");
        String bikeBrand = scanner.nextLine();

        System.out.println("\n--- Demonstrating Constructor Chaining ---");
        Vehicle myCar = new Car(carBrand);
        Vehicle myBike = new Bike(bikeBrand);

        System.out.println("\n--- Demonstrating Runtime Polymorphism ---");
        myCar.display();
        myCar.start(); 

        System.out.println();

        myBike.display();
        myBike.start(); 

        scanner.close();
    }
}