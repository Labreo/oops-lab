
package Exp4;

import java.util.Scanner;

class Student {
    private int rollNumber;
    private String name;
    private double marks;

    public Student() {
        this.rollNumber = 0;
        this.name = "Unknown";
        this.marks = 0.0;
    }

    public Student(int rollNumber, String name, double marks) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.marks = marks;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

  
    @Override
    public String toString() {
        return "Student Details:\n" +
               "Roll Number: " + rollNumber + "\n" +
               "Name: " + name + "\n" +
               "Marks: " + marks + "\n";
    }
}

public class StudentRecordManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Enter the number of students (N): ");
        int N = scanner.nextInt();
        scanner.nextLine();


        Student[] students = new Student[N];
        double totalMarksSum = 0;

       
        for (int i = 0; i < N; i++) {
            System.out.println("\nEnter details for student " + (i + 1) + ":");
            
         
            System.out.print("Enter Roll Number: ");
            int roll = scanner.nextInt();
            scanner.nextLine(); 
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Marks: ");
            double marks = scanner.nextDouble();
            scanner.nextLine();

            students[i] = new Student(roll, name, marks);
            
           
            if (i == 0) {
                 Student defaultStudent = new Student();
                 defaultStudent.setRollNumber(1);
                
            }

            totalMarksSum += students[i].getMarks();
        }

        System.out.println("\n--- Student Details ---");
        for (Student student : students) {
            System.out.println(student.toString());
        }

        double averageMarks = totalMarksSum / N;
        System.out.println("--- Summary ---");
        System.out.println("Total students: " + N);
        System.out.println("Average marks of all students: " + averageMarks);

        scanner.close();
    }
}
