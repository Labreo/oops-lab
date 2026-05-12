package Exp12;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
        return "Roll Number: " + rollNumber + " | Name: " + name + " | Marks: " + marks;
    }
}

public class StudentRecordManagement {
    public static void main(String[] args) {
        String inputFileName = "input1.txt";
        String outputFileName = "passed_students.txt";
        double passingMarks = 40.0; 

        try (Scanner scanner = new Scanner(new File(inputFileName));
             PrintWriter writer = new PrintWriter(new File(outputFileName))) {

            if (!scanner.hasNextInt()) {
                System.out.println("The input file is empty or formatted incorrectly.");
                return;
            }
            int N = scanner.nextInt();
            scanner.nextLine();

            Student[] students = new Student[N];
            double totalMarksSum = 0;

            for (int i = 0; i < N; i++) {
                int roll = scanner.nextInt();
                scanner.nextLine();
                
                String name = scanner.nextLine();
                
                double marks = scanner.nextDouble();
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }

                students[i] = new Student(roll, name, marks);
                totalMarksSum += marks;
            }

            double averageMarks = totalMarksSum / N;

            writer.println("----- PASSED STUDENTS -----");
            for (Student student : students) {
                if (student.getMarks() >= passingMarks) {
                    writer.println(student.toString());
                }
            }

            writer.println("\n----- CLASS STATISTICS -----");
            writer.println("Total students evaluated: " + N);
            writer.printf("Class Average Marks: %.2f\n", averageMarks);

            System.out.println("Success! Data read from '" + inputFileName + "'.");
            System.out.println("Passed student details and averages written to '" + outputFileName + "'.");

        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not find the file '" + inputFileName + "'.");
            System.out.println("Please create an 'input.txt' file in your project directory.");
        }
    }
}