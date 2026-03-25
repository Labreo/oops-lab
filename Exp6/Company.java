package Exp6;
import java.util.Scanner;

class Person {
    private String name;
    private int age;

    public Person() {
        this.name = "Unknown";
        this.age = 0;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public void displayPersonDetails() {
        System.out.println("Name: " + name + ", Age: " + age);
    }

    @Override
    public String toString() {
        return "Person [Name: " + name + ", Age: " + age + "]";
    }
}

class Employee extends Person {
    private int employeeId;
    private double salary;

    public Employee() {
        super();
        this.employeeId = 0;
        this.salary = 0.0;
    }

    public Employee(String name, int age, int employeeId, double salary) {
        super(name, age);
        this.employeeId = employeeId;
        this.salary = salary;
    }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public void displayEmployeeDetails() {
        displayPersonDetails();
        System.out.println("Employee ID: " + employeeId + ", Salary: " + salary);
    }

    @Override
    public String toString() {
        return super.toString() + "\nEmployee [ID: " + employeeId + ", Salary: " + salary + "]";
    }
}

public class Company {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();

        Employee emp = new Employee(name, age, id, salary);

        System.out.println("\nDisplaying Employee Details:");
        emp.displayEmployeeDetails();

        System.out.println("\ntoString Output:");
        System.out.println(emp);

        sc.close();
    }
}