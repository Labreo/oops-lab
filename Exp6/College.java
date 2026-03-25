package Exp6;
import java.util.Scanner;

class person {
    public String name;
    public int age;

    public person() {
        this.name = "Unknown";
        this.age = 0;
    }

    public person(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("Person constructor called.");
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return "Person [Name: " + name + ", Age: " + age + "]";
    }
}

class Staff extends person {
    public int staffId;

    public Staff() {
        super();
        this.staffId = 0;
    }

    public Staff(String name, int age, int staffId) {
        super(name, age);
        this.staffId = staffId;
        System.out.println("Staff constructor called.");
    }

    public int getStaffId() { return staffId; }
    public void setStaffId(int staffId) { this.staffId = staffId; }

    @Override
    public String toString() {
        return super.toString() + "\nStaff [Staff ID: " + staffId + "]";
    }
}

class Professor extends Staff {
    private String department;
    private String researchArea;

    public Professor() {
        super();
        this.department = "None";
        this.researchArea = "None";
    }

    public Professor(String name, int age, int staffId, String department, String researchArea) {
        super(name, age, staffId);
        this.department = department;
        this.researchArea = researchArea;
        System.out.println("Professor constructor called.");
    }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public String getResearchArea() { return researchArea; }
    public void setResearchArea(String researchArea) { this.researchArea = researchArea; }

    @Override
    public String toString() {
        return super.toString() + "\nProfessor [Department: " + department + ", Research Area: " + researchArea + "]";
    }
}

public class College {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Staff ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Department: ");
        String dep = sc.nextLine();
        System.out.print("Enter Research Area: ");
        String ra = sc.nextLine();

        System.out.println("\nConstructor Execution Order:");
        Professor prof = new Professor(name, age, id, dep, ra);

        System.out.println("\nProfessor Details:");
        System.out.println(prof);

        sc.close();
    }
}