package Exp7;
import java.util.Scanner;

class Employee {
    private String name;
    protected double salary;

    public Employee() {
        this.name = "Unknown";
        this.salary = 0.0;
    }

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() { 
        return name; 
    }
    
    public void setName(String name) { 
        this.name = name; 
    }
    
    public double getSalary() { 
        return salary; 
    }
    
    public void setSalary(double salary) { 
        this.salary = salary; 
    }

    public double calculateBonus() {
        return 0.0;
    }

    @Override
    public String toString() {
        return "Employee [Name=" + name + ", Salary=" + salary + "]";
    }
}

class FullTimeEmployee extends Employee {
    public FullTimeEmployee() {
        super();
    }

    public FullTimeEmployee(String name, double salary) {
        super(name, salary);
    }

    @Override
    public double calculateBonus() {
        return salary * 0.20;
    }

    @Override
    public String toString() {
        return "FullTimeEmployee [Name=" + getName() + ", Salary=" + getSalary() + "]";
    }
}

class PartTimeEmployee extends Employee {
    public PartTimeEmployee() {
        super();
    }

    public PartTimeEmployee(String name, double salary) {
        super(name, salary);
    }

    @Override
    public double calculateBonus() {
        return salary * 0.10;
    }

    @Override
    public String toString() {
        return "PartTimeEmployee [Name=" + getName() + ", Salary=" + getSalary() + "]";
    }
}

public class Q2_EmployeeSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Q2: Employee Bonus System ---");
        
        System.out.print("Enter Full-Time Employee Name: ");
        String ftName = scanner.nextLine();
        System.out.print("Enter Full-Time Employee Salary: ");
        double ftSalary = scanner.nextDouble();
        scanner.nextLine();

        FullTimeEmployee ftEmp = new FullTimeEmployee(ftName, ftSalary);

        System.out.print("\nEnter Part-Time Employee Name: ");
        String ptName = scanner.nextLine();
        
        PartTimeEmployee ptEmp = new PartTimeEmployee();
        ptEmp.setName(ptName);
        System.out.print("Enter Part-Time Employee Salary: ");
        ptEmp.setSalary(scanner.nextDouble());

        System.out.println("\nEmployee Details:");
        System.out.println(ftEmp.toString() + " -> Bonus: " + ftEmp.calculateBonus());
        System.out.println(ptEmp.toString() + " -> Bonus: " + ptEmp.calculateBonus());

        scanner.close();
    }
}