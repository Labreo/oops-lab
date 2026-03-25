package Exp6;

import java.util.Scanner;

class person {
    public String name;
    public int age;

    public person(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("This is the person class.");
    }

  
}

class Staff extends person {
    public int staffId;

    public Staff(String name, int age, int staffId) {
        super(name, age);
        this.staffId = staffId;
          System.out.println("This is the staff class.");
    }


}

class Professor extends Staff {
    final private String department;
    final private String researchArea;

    public Professor(String name, int age, int staffId, String department, String researchArea) {
        super(name, age, staffId);
        this.department = department;
        this.researchArea = researchArea;
             System.out.println("This is the professer class.");
    }

    @Override
     public String toString(){
 
      return "The person name is " + name + " and thier age is " + age + ".\n"+"The departement of professor is " + department + " and thier research area is " + researchArea + ".\n"+"The staff id is " + staffId + ".\n";
    }
}

public class College {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the professer:");
        String name = sc.nextLine();
   
        System.out.println("Enter the age of the professer:");
       int age = sc.nextInt();
       System.out.println("Enter the staff ID of the professer:");
       int id = sc.nextInt();
       System.out.println("Enter the departement of the professer:");
        String dep = sc.nextLine();
          sc.nextLine();
       System.out.println("Enter the research Area of the professer:");
        String ra = sc.nextLine();

        Professor prof = new Professor(name, age, id, dep, ra);
        System.out.println(prof);
        sc.close();
    }
}
