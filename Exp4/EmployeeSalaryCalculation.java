package Exp4;

import java.util.Scanner;

class Employee {
    private int employeeid;
    private String name;
    private double basicsalary;

    public Employee() {
        this.employeeid = 0;
        this.name = "Unknown";
        this.basicsalary = 0;
    }

    public Employee(int employeeid, String name, double basicsalary) {
        this.employeeid = employeeid;
        this.name = name;
        this.basicsalary = basicsalary;
    }

    public int getEmployeeid() {
        return employeeid;
    }

    public String getName() {
        return name;
    }

    public double getBasicsalary() {
        return basicsalary;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBasicsalary(double basicsalary) {
        this.basicsalary = basicsalary;
    }

    public double calculateGrossSalary() {
        double allowances = 0.2 * basicsalary; 
        return basicsalary + allowances;
    }

    @Override
    public String toString() {
        return "Employee Details:\n" +
               "Employee ID: " + employeeid + "\n" +
               "Name: " + name + "\n" +
               "Basic Salary: " + basicsalary + "\n" +
               "Gross Salary: " + calculateGrossSalary() + "\n";
    }
}

public class EmployeeSalaryCalculation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of employees: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        Employee[] employees = new Employee[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Employee " + (i + 1));

            System.out.print("Employee ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Basic Salary: ");
            double salary = scanner.nextDouble();

            employees[i] = new Employee();
            employees[i].setEmployeeid(id);
            employees[i].setName(name);
            employees[i].setBasicsalary(salary);
        }

        System.out.println("\n----- Employee Salary Details -----");
        for (Employee emp : employees) {
            System.out.println(emp);
        }

        scanner.close();
    }
}