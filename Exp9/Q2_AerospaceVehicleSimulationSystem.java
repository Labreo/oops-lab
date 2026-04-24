package Exp9;

import java.util.Scanner;

interface Propellable {
    double engineThrust(double fuelFlow);

    default double fuelEfficiency(double thrust, double fuelBurn) {
        return thrust / (fuelBurn * 9.81);
    }
}

interface AeroDynamic extends Propellable {
    double liftCoefficient(double velocity, double wingArea);

    default double dragForce(double Cd, double airDensity, double velocity, double refArea) {
        return 0.5 * Cd * airDensity * Math.pow(velocity, 2) * refArea;
    }

    default double liftToDragRatio(double lift, double drag) {
        return lift / drag;
    }
}

interface SupersonicCapable extends AeroDynamic {
    double machNumber(double velocity, double speedOfSound);

    default double sonicBoom(double flightAltitude, double machNo) {
        return 0.53 * Math.pow(machNo, 2) / Math.sqrt(flightAltitude);
    }

    default double heatGenerated(double machNo) {
        double ambientTemp = 288.15;
        return ambientTemp * (1 + 0.2 * Math.pow(machNo, 2));
    }
}

interface Trackable {
    default double radarCrossSection(double length, double width) {
        double lambda = 0.03;
        return (Math.PI * length * width) / Math.pow(lambda, 2);
    }

    String currentPosition(double time);
}

interface MissionPlannable extends Trackable {
    double missionRange(double fuelCapacity, double burnRate);

    default double combatRadius(double totalRange) {
        return totalRange / 2.5;
    }
}

class Vehicle {
    public String vehicleId;
    public double mass;
    public double maxSpeed;

    public Vehicle() {
        this.vehicleId = "Unknown";
        this.mass = 0.0;
        this.maxSpeed = 0.0;
    }

    public Vehicle(String vehicleId, double mass, double maxSpeed) {
        this.vehicleId = vehicleId;
        this.mass = mass;
        this.maxSpeed = maxSpeed;
    }

    public double kineticEnergy(double velocity) {
        return 0.5 * this.mass * Math.pow(velocity, 2);
    }

    @Override
    public String toString() {
        return "Vehicle [ID: " + vehicleId + ", Mass: " + mass + " kg, Max Speed: " + maxSpeed + " m/s]";
    }
}

class Aircraft extends Vehicle implements AeroDynamic {
    public double wingSpan;
    public double altitude;
    public double fuelCapacity;

    public Aircraft() {
        super();
        this.wingSpan = 0.0;
        this.altitude = 0.0;
        this.fuelCapacity = 0.0;
    }

    public Aircraft(String vehicleId, double mass, double maxSpeed, double wingSpan, double altitude,
            double fuelCapacity) {
        super(vehicleId, mass, maxSpeed);
        this.wingSpan = wingSpan;
        this.altitude = altitude;
        this.fuelCapacity = fuelCapacity;
    }

    @Override
    public double engineThrust(double fuelFlow) {
        return fuelFlow * 45000;
    }

    @Override
    public double liftCoefficient(double velocity, double wingArea) {
        double alpha = 0.087;
        return 2 * Math.PI * alpha;
    }

    @Override
    public String toString() {
        return super.toString() + " -> Aircraft [WingSpan: " + wingSpan + " m, Altitude: " + altitude
                + " m, Fuel Capacity: " + fuelCapacity + " kg]";
    }
}

class FighterJet extends Aircraft implements SupersonicCapable, MissionPlannable {

    public FighterJet() {
        super();
    }

    public FighterJet(String vehicleId, double mass, double maxSpeed, double wingSpan, double altitude,
            double fuelCapacity) {
        super(vehicleId, mass, maxSpeed, wingSpan, altitude, fuelCapacity);
    }
 
    @Override
    public double machNumber(double velocity, double speedOfSound) {
        return velocity / speedOfSound;
    }

    @Override
    public double dragForce(double Cd, double airDensity, double velocity, double refArea) {
        double baseDrag = SupersonicCapable.super.dragForce(Cd, airDensity, velocity, refArea);
        double machNo = machNumber(velocity, 343.0);

        if (machNo > 1.0) {
            double dynamicPressure = 0.5 * airDensity * Math.pow(velocity, 2);
            double waveDrag = 0.1 * Math.pow(machNo - 1, 2) * refArea * dynamicPressure;
            return baseDrag + waveDrag;
        }
        return baseDrag;
    }

    @Override
    public double missionRange(double fuelCapacity, double burnRate) {
        double initialMass = this.mass + (0.95 * fuelCapacity);
        double finalMass = this.mass + (0.40 * fuelCapacity);

        double thrust = engineThrust(burnRate);
        double isp = fuelEfficiency(thrust, burnRate);
        double cruiseVelocity = this.maxSpeed * 0.8;

        double refArea = this.wingSpan * (this.wingSpan / 4.0);
        double liftCoef = liftCoefficient(cruiseVelocity, refArea);

        double lift = liftCoef * 0.5 * 1.225 * Math.pow(cruiseVelocity, 2) * refArea;
        double drag = dragForce(0.025, 1.225, cruiseVelocity, refArea);
        double ldRatio = liftToDragRatio(lift, drag);

        return (isp * cruiseVelocity * ldRatio) * Math.log(initialMass / finalMass);
    }

    @Override
    public String currentPosition(double time) {
        double headingAngle = 45.0;
        double x = this.maxSpeed * time * Math.cos(Math.toRadians(headingAngle));
        double y = this.maxSpeed * time * Math.sin(Math.toRadians(headingAngle));
        return String.format("X: %.2f, Y: %.2f", x, y);
    }

    @Override
    public String toString() {
        return super.toString() + " -> FighterJet [Operational]";
    }
}

public class Q2_AerospaceVehicleSimulationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Fighter Jet ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter empty mass (kg): ");
        double mass = scanner.nextDouble();

        System.out.print("Enter max speed (m/s): ");
        double maxSpeed = scanner.nextDouble();

        System.out.print("Enter wingspan (m): ");
        double wingSpan = scanner.nextDouble();

        System.out.print("Enter current altitude (m): ");
        double altitude = scanner.nextDouble();

        System.out.print("Enter max fuel capacity (kg): ");
        double fuelCapacity = scanner.nextDouble();

        System.out.print("Enter nominal fuel burn rate (kg/s): ");
        double burnRate = scanner.nextDouble();

        FighterJet jet = new FighterJet(id, mass, maxSpeed, wingSpan, altitude, fuelCapacity);

        System.out.println("\n--- Vehicle Details ---");
        System.out.println(jet.toString());

        double maxRange = jet.missionRange(jet.fuelCapacity, burnRate);
        double combatRadius = jet.combatRadius(maxRange);

        double heatGenerated = jet.heatGenerated(2.2);

        double sonicBoomOverpressure = jet.sonicBoom(15000, 2.2);

        double kineticEnergy = jet.kineticEnergy(jet.maxSpeed);

        System.out.println("\n--- Mission Simulation Results ---");
        System.out.printf("Max Range (Breguet, 95%% to 40%% fuel): %.2f meters\n", maxRange);
        System.out.printf("Combat Radius: %.2f meters\n", combatRadius);
        System.out.printf("Stagnation Heat at Mach 2.2: %.2f K\n", heatGenerated);
        System.out.printf("Sonic Boom Overpressure (15000m, Mach 2.2): %.2f Pascals\n", sonicBoomOverpressure);
        System.out.printf("Kinetic Energy at Max Speed: %.2f Joules\n", kineticEnergy);

        scanner.close();
    }
}