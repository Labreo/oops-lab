package Exp7;
import java.util.Scanner;

class Vehicle {
    private double speed;

    public Vehicle() {
        this.speed = 0.0;
    }

    public Vehicle(double speed) {
        this.speed = speed;
    }

    public double getSpeed() { 
        return speed; 
    }
    
    public void setSpeed(double speed) { 
        this.speed = speed; 
    }

    public void displayDetails() {
        System.out.println("Vehicle Speed: " + speed + " km/h");
    }

    @Override
    public String toString() {
        return "Vehicle [Speed=" + speed + "]";
    }
}

class Car extends Vehicle {
    private String fuelType;

    public Car() {
        super();
        this.fuelType = "Unknown";
    }

    public Car(double speed, String fuelType) {
        super(speed);
        this.fuelType = fuelType;
    }

    public String getFuelType() { 
        return fuelType; 
    }
    
    public void setFuelType(String fuelType) { 
        this.fuelType = fuelType; 
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Car Fuel Type: " + fuelType);
    }

    @Override
    public String toString() {
        return "Car [Speed=" + getSpeed() + ", FuelType=" + fuelType + "]";
    }
}

public class Q4_VehicleSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Q4: Polymorphism with Constructors & super ---");
        
        System.out.print("Enter car speed (km/h): ");
        double speed = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.print("Enter car fuel type (e.g., Petrol, Diesel, Electric): ");
        String fuelType = scanner.nextLine();

        Car myCar = new Car(speed, fuelType);

        System.out.println("\nObject Details (toString):");
        System.out.println(myCar.toString());

        System.out.println("\nDisplaying details:");
        myCar.displayDetails();

        scanner.close();
    }
}