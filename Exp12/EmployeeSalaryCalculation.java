package Exp12;

import java.io.*;
import java.util.Scanner;

class Employee {
    private int employeeid;
    private String name;
    private double basicsalary;

    public Employee() {
        this.employeeid = 0;
        this.name = "Unknown";
        this.basicsalary = 0.0;
    }

    public Employee(int employeeid, String name, double basicsalary) {
        this.employeeid = employeeid;
        this.name = name;
        this.basicsalary = basicsalary;
    }
    public int getEmployeeid() { return employeeid; }
    public String getName() { return name; }
    public double getBasicsalary() { return basicsalary; }
    public void setEmployeeid(int employeeid) { this.employeeid = employeeid; }
    public void setName(String name) { this.name = name; }
    public void setBasicsalary(double basicsalary) { this.basicsalary = basicsalary; }

    public double calculateGrossSalary() {
        double allowances = 0.2 * basicsalary;
        return basicsalary + allowances;
    }

    @Override
    public String toString() {
        return "Employee ID: " + employeeid + "\n" +
               "Name: " + name + "\n" +
               "Basic Salary: " + basicsalary + "\n" +
               "Gross Salary: " + calculateGrossSalary() + "\n";
    }
}

public class EmployeeSalaryCalculation {
    public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
        String inputFileName = sc.nextLine();//input2.txt
        sc.close();
        String outputFileName = "output.txt";
        File inputFile = new File(inputFileName);
        if (!inputFile.exists()) {
            System.out.println("Error: The file '" + inputFileName + "' does not exist.");
            return;
        }
        if (!inputFile.canRead()) {
            System.out.println("Error: Do not have read permissions for '" + inputFileName + "'.");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) { 
            String line = reader.readLine();
            if (line == null) {
                System.out.println("Input file is empty.");
                return;
            }
            int n = Integer.parseInt(line.trim());
            Employee[] employees = new Employee[n];

            for (int i = 0; i < n; i++) {
                int id = Integer.parseInt(reader.readLine().trim());
                String name = reader.readLine().trim();
                double salary = Double.parseDouble(reader.readLine().trim());

                if (i % 2 == 0) {
                    employees[i] = new Employee(id, name, salary);
                } else {
                    employees[i] = new Employee();
                    employees[i].setEmployeeid(id);
                    employees[i].setName(name);
                    employees[i].setBasicsalary(salary);
                }
            }

    
            writer.write("Employee Salary Details\n");
            writer.write("========================\n");
            for (Employee emp : employees) {
                writer.write(emp.toString());
                writer.write("------------------------\n");
            }

            System.out.println("Success! Data read from " + inputFileName + " and written to " + outputFileName);

        } catch (IOException e) {
  
            System.out.println("An I/O Error occurred: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Data formatting error in input file. Please ensure data is properly formatted: " + e.getMessage());
        }
    }
}