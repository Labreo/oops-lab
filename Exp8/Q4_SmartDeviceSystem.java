package Exp8;

import java.util.Scanner;

interface SmartFeatures {
    void connectToWiFi(String networkName);
}

abstract class Device {
    public static void deviceInfo() {
        System.out.println("[Device Info] This is a base electronic device blueprint.");
    }

    public abstract void operate();
}

class SmartTV extends Device implements SmartFeatures {
    private String brand;

    public SmartTV(String brand) {
        this.brand = brand;
    }

    @Override
    public void connectToWiFi(String networkName) {
        System.out.println(brand + " Smart TV successfully connected to WiFi network: " + networkName);
    }

    @Override
    public void operate() {
        System.out.println(brand + " Smart TV is now operating. Streaming applications are ready.");
    }
}

public class Q4_SmartDeviceSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Q4: Smart Device System ---");
        
        System.out.println("Calling static method from abstract class:");
        Device.deviceInfo();

        System.out.println();
        
        System.out.print("Enter Smart TV brand: ");
        String tvBrand = scanner.nextLine();
        
        System.out.print("Enter WiFi Network Name to connect: ");
        String wifiName = scanner.nextLine();

        System.out.println("\n--- Demonstrating Multiple Inheritance (Interface + Abstract Class) ---");
        SmartTV myTV = new SmartTV(tvBrand);
        
        myTV.connectToWiFi(wifiName);
        myTV.operate();

        scanner.close();
    }
}