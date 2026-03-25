package Exp6;
import java.util.Scanner;

abstract class Shape {
    private String color;

    public Shape() {
        this.color = "White";
    }

    public Shape(String color) {
        this.color = color;
    }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public abstract double computeArea();

    public void displayColor() {
        System.out.println("Color: " + color);
    }

    @Override
    public String toString() {
        return "Shape [Color: " + color + "]";
    }
}

class Circle extends Shape {
    private double radius;

    public Circle() {
        super();
        this.radius = 0;
    }

    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    public double getRadius() { return radius; }
    public void setRadius(double radius) { this.radius = radius; }

    @Override
    public double computeArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return super.toString() + "\nCircle [Radius: " + radius + ", Area: " + String.format("%.2f", computeArea()) + "]";
    }
}

class Rectangle extends Shape {
    private double length;
    private double width;

    public Rectangle() {
        super();
        this.length = 0;
        this.width = 0;
    }

    public Rectangle(String color, double length, double width) {
        super(color);
        this.length = length;
        this.width = width;
    }

    public double getLength() { return length; }
    public void setLength(double length) { this.length = length; }
    public double getWidth() { return width; }
    public void setWidth(double width) { this.width = width; }

    @Override
    public double computeArea() {
        return length * width;
    }

    @Override
    public String toString() {
        return super.toString() + "\nRectangle [Length: " + length + ", Width: " + width + ", Area: " + String.format("%.2f", computeArea()) + "]";
    }
}

public class Shapes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Shape[] shapes = new Shape[2];

        System.out.print("Enter Circle Color: ");
        String color = sc.nextLine();
        System.out.print("Enter Radius: ");
        double radius = sc.nextDouble();
        sc.nextLine();
        shapes[0] = new Circle(color, radius);

        System.out.print("Enter Rectangle Color: ");
        color = sc.nextLine();
        System.out.print("Enter Length: ");
        double length = sc.nextDouble();
        System.out.print("Enter Width: ");
        double width = sc.nextDouble();
        sc.nextLine();
        shapes[1] = new Rectangle(color, length, width);

        System.out.println("\nDynamic Binding - computeArea() via Shape reference:");
        for (Shape s : shapes) {
            System.out.println(s);
            s.displayColor();
            System.out.println("Computed Area: " + String.format("%.2f", s.computeArea()));
            System.out.println();
        }

        sc.close();
    }
}