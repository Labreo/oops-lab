package Exp6;

class Person {
    private String name;
    private int age;
    public Person(String name,int age){
        this.name = name;
        this.age = age;
    }
    public void displayPersonDetails(){
        System.out.println("The person name is "+name+"and thier age is "+age+".\n");
    }
}

class Employee extends Person{
    int employeeId;
    double salary;
     public Employee(String name, int age, int employeeId, double salary) {
        super(name, age);
        this.employeeId = employeeId;
        this.salary = salary;
        System.out.println("Employee constructor called.");
    }
    public void displayEmployeeDetails() {
   
        displayPersonDetails();
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Salary: " + salary);
    }
}
public class Company {
    public static void main(String[] args) {
        System.out.println("Creating an Employee object:");
        Employee emp = new Employee("Alice Smith", 30, 101, 75000.00);

        System.out.println("\nDisplaying Employee details:");
       
        emp.displayEmployeeDetails();
    }
}
