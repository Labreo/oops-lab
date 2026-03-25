package Exp6;
import java.util.Scanner;

class Vehicle {
    protected int vehicleNumber;
    protected String ownerName;

    public Vehicle() {
        this.vehicleNumber = 0;
        this.ownerName = "Unknown";
    }

    public Vehicle(int vehicleNumber, String ownerName) {
        this.vehicleNumber = vehicleNumber;
        this.ownerName = ownerName;
    }

    public int getVehicleNumber() { return vehicleNumber; }
    public void setVehicleNumber(int vehicleNumber) { this.vehicleNumber = vehicleNumber; }
    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public int calculateServiceCost() {
        return 0;
    }

    @Override
    public String toString() {
        return "Vehicle [Number: " + vehicleNumber + ", Owner: " + ownerName + "]";
    }
}

class Car extends Vehicle {
    private int engineCapacity;
    private int serviceHours;
    private int sparePartCost;

    public Car() {
        super();
        this.engineCapacity = 0;
        this.serviceHours = 0;
        this.sparePartCost = 0;
    }

    public Car(int vehicleNumber, String ownerName, int engineCapacity, int serviceHours, int sparePartCost) {
        super(vehicleNumber, ownerName);
        this.engineCapacity = engineCapacity;
        this.serviceHours = serviceHours;
        this.sparePartCost = sparePartCost;
    }

    public int getEngineCapacity() { return engineCapacity; }
    public void setEngineCapacity(int engineCapacity) { this.engineCapacity = engineCapacity; }
    public int getServiceHours() { return serviceHours; }
    public void setServiceHours(int serviceHours) { this.serviceHours = serviceHours; }
    public int getSparePartCost() { return sparePartCost; }
    public void setSparePartCost(int sparePartCost) { this.sparePartCost = sparePartCost; }

    @Override
    public int calculateServiceCost() {
        return (engineCapacity * 2) + (serviceHours * 100) + sparePartCost;
    }

    @Override
    public String toString() {
        return super.toString() + "\nCar [Engine: " + engineCapacity + "cc, Hours: " + serviceHours + ", Spare Parts: " + sparePartCost + "]";
    }
}

class Bike extends Vehicle {
    private int engineCapacity;
    private int serviceHours;
    private int sparePartCost;

    public Bike() {
        super();
        this.engineCapacity = 0;
        this.serviceHours = 0;
        this.sparePartCost = 0;
    }

    public Bike(int vehicleNumber, String ownerName, int engineCapacity, int serviceHours, int sparePartCost) {
        super(vehicleNumber, ownerName);
        this.engineCapacity = engineCapacity;
        this.serviceHours = serviceHours;
        this.sparePartCost = sparePartCost;
    }

    public int getEngineCapacity() { return engineCapacity; }
    public void setEngineCapacity(int engineCapacity) { this.engineCapacity = engineCapacity; }
    public int getServiceHours() { return serviceHours; }
    public void setServiceHours(int serviceHours) { this.serviceHours = serviceHours; }
    public int getSparePartCost() { return sparePartCost; }
    public void setSparePartCost(int sparePartCost) { this.sparePartCost = sparePartCost; }

    @Override
    public int calculateServiceCost() {
        return engineCapacity + (serviceHours * 80) + sparePartCost;
    }

    @Override
    public String toString() {
        return super.toString() + "\nBike [Engine: " + engineCapacity + "cc, Hours: " + serviceHours + ", Spare Parts: " + sparePartCost + "]";
    }
}

class Truck extends Vehicle {
    private int engineCapacity;
    private int serviceHours;
    private int sparePartCost;

    public Truck() {
        super();
        this.engineCapacity = 0;
        this.serviceHours = 0;
        this.sparePartCost = 0;
    }

    public Truck(int vehicleNumber, String ownerName, int engineCapacity, int serviceHours, int sparePartCost) {
        super(vehicleNumber, ownerName);
        this.engineCapacity = engineCapacity;
        this.serviceHours = serviceHours;
        this.sparePartCost = sparePartCost;
    }

    public int getEngineCapacity() { return engineCapacity; }
    public void setEngineCapacity(int engineCapacity) { this.engineCapacity = engineCapacity; }
    public int getServiceHours() { return serviceHours; }
    public void setServiceHours(int serviceHours) { this.serviceHours = serviceHours; }
    public int getSparePartCost() { return sparePartCost; }
    public void setSparePartCost(int sparePartCost) { this.sparePartCost = sparePartCost; }

    @Override
    public int calculateServiceCost() {
        return (engineCapacity * 3) + (serviceHours * 150) + sparePartCost;
    }

    @Override
    public String toString() {
        return super.toString() + "\nTruck [Engine: " + engineCapacity + "cc, Hours: " + serviceHours + ", Spare Parts: " + sparePartCost + "]";
    }
}

public class Garage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Vehicle v;

        System.out.println("--- Car Details ---");
        System.out.print("Vehicle Number: ");
        int vn = sc.nextInt();
        sc.nextLine();
        System.out.print("Owner Name: ");
        String owner = sc.nextLine();
        System.out.print("Engine Capacity (cc): ");
        int engine = sc.nextInt();
        System.out.print("Service Hours: ");
        int hours = sc.nextInt();
        System.out.print("Spare Part Cost: ");
        int spare = sc.nextInt();
        sc.nextLine();
        v = new Car(vn, owner, engine, hours, spare);
        System.out.println(v);
        System.out.println("Service Cost: Rs. " + v.calculateServiceCost());

        System.out.println("\n--- Bike Details ---");
        System.out.print("Vehicle Number: ");
        vn = sc.nextInt();
        sc.nextLine();
        System.out.print("Owner Name: ");
        owner = sc.nextLine();
        System.out.print("Engine Capacity (cc): ");
        engine = sc.nextInt();
        System.out.print("Service Hours: ");
        hours = sc.nextInt();
        System.out.print("Spare Part Cost: ");
        spare = sc.nextInt();
        sc.nextLine();
        v = new Bike(vn, owner, engine, hours, spare);
        System.out.println(v);
        System.out.println("Service Cost: Rs. " + v.calculateServiceCost());

        System.out.println("\n--- Truck Details ---");
        System.out.print("Vehicle Number: ");
        vn = sc.nextInt();
        sc.nextLine();
        System.out.print("Owner Name: ");
        owner = sc.nextLine();
        System.out.print("Engine Capacity (cc): ");
        engine = sc.nextInt();
        System.out.print("Service Hours: ");
        hours = sc.nextInt();
        System.out.print("Spare Part Cost: ");
        spare = sc.nextInt();
        sc.nextLine();
        v = new Truck(vn, owner, engine, hours, spare);
        System.out.println(v);
        System.out.println("Service Cost: Rs. " + v.calculateServiceCost());

        sc.close();
    }
}