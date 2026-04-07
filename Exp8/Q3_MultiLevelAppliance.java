package Exp8;

import java.util.Scanner;

abstract class Appliance {
    public abstract void powerOn();
}

abstract class WashingMachine extends Appliance {
    public abstract void wash();
}

class AutomaticMachine extends WashingMachine {
    private String modelName;

    public AutomaticMachine(String modelName) {
        this.modelName = modelName;
    }

    @Override
    public void powerOn() {
        System.out.println(modelName + " is now powered ON.");
    }

    @Override
    public void wash() {
        System.out.println(modelName + " is automatically detecting load and washing clothes.");
    }
}

public class Q3_MultiLevelAppliance {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Q3: Multi-Level Appliance System ---");
        System.out.print("Enter Automatic Washing Machine model name: ");
        String model = scanner.nextLine();

        AutomaticMachine myMachine = new AutomaticMachine(model);

        System.out.println("\n--- Operating Appliance ---");
        myMachine.powerOn();
        myMachine.wash();

        scanner.close();
    }
}