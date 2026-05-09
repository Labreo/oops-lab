package Exp11;

import java.util.*;

class Employee {
    private String name;
    private String department;
    private double salary;

    public Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return String.format("Name: %-15s | Salary: %.2f", name, salary);
    }
}

class SalaryComparator implements Comparator<Employee> {
    @Override
    public int compare(final Employee e1, final Employee e2) {
        return Double.compare(e2.getSalary(), e1.getSalary());
    }
}

public class Q4_Vector {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Vector<Employee> employees = new Vector<>();

        System.out.print("Enter the number of employees: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Employee " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Department: ");
            String dept = sc.nextLine();
            System.out.print("Salary: ");
            double salary = sc.nextDouble();
            sc.nextLine();

            employees.add(new Employee(name, dept, salary));
        }

        System.out.println("\nEmployees before sorting:");
        for (Employee e : employees) {
            System.out.println(e);
        }

        Collections.sort(employees, new SalaryComparator());

        System.out.println("\nEmployees ranked by Salary (Descending):");
        for (Employee e : employees) {
            System.out.println(e);
        }

        sc.close();
    }
}