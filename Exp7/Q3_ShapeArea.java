package Exp7;
import java.util.Scanner;

class Shape {
    private String shapeName;

    public Shape() {
        this.shapeName = "Generic Shape";
    }

    public Shape(String shapeName) {
        this.shapeName = shapeName;
    }

    public String getShapeName() { 
        return shapeName; 
    }
    
    public void setShapeName(String shapeName) { 
        this.shapeName = shapeName; 
    }

    public double area() {
        System.out.println("Area method of base Shape class.");
        return 0.0;
    }

    @Override
    public String toString() {
        return "Shape [Name=" + shapeName + "]";
    }
}

class Circle extends Shape {
    private double radius;

    public Circle() {
        super("Circle");
        this.radius = 1.0;
    }

    public Circle(double radius) {
        super("Circle");
        this.radius = radius;
    }

    public double getRadius() { 
        return radius; 
    }
    
    public void setRadius(double radius) { 
        this.radius = radius; 
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return "Circle [Radius=" + radius + "]";
    }
}

class Rectangle extends Shape {
    private double length;
    private double width;

    public Rectangle() {
        super("Rectangle");
        this.length = 1.0;
        this.width = 1.0;
    }

    public Rectangle(double length, double width) {
        super("Rectangle");
        this.length = length;
        this.width = width;
    }

    public double getLength() { 
        return length; 
    }
    
    public void setLength(double length) { 
        this.length = length; 
    }
    
    public double getWidth() { 
        return width; 
    }
    
    public void setWidth(double width) { 
        this.width = width; 
    }

    @Override
    public double area() {
        return length * width;
    }

    @Override
    public String toString() {
        return "Rectangle [Length=" + length + ", Width=" + width + "]";
    }
}

public class Q3_ShapeArea {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Q3: Dynamic Method Dispatch ---");
        
        System.out.print("Enter radius for Circle: ");
        double radius = scanner.nextDouble();
        
        System.out.print("Enter length for Rectangle: ");
        double length = scanner.nextDouble();
        System.out.print("Enter width for Rectangle: ");
        double width = scanner.nextDouble();

        Shape shape1 = new Circle(radius);
        Shape shape2 = new Rectangle(length, width);

        System.out.println("\nObject Details:");
        System.out.println(shape1.toString());
        System.out.println(shape2.toString());

        System.out.println("\nCalculating Areas:");
        System.out.println("Area of " + shape1.getShapeName() + ": " + shape1.area());
        System.out.println("Area of " + shape2.getShapeName() + ": " + shape2.area());

        scanner.close();
    }
}