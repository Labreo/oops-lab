package Exp6;

class person{
    private String name;
    private int age;
    public person(String name,int age){
        this.name = name;
        this.age = age;
    }
     public void displayPersonDetails(){
        System.out.println("The person name is "+name+"and thier age is "+age+".\n");
    }
}
class Staff extends person{
    private int staffId;
    public Staff(String name,int age,int staffId){
        super(name, age);
        this.staffId = staffId;
    }
     public void displayStaffDetails(){
        displayPersonDetails();
        System.out.println("The staff id is "+staffId+".\n");
    }

}

class Professor extends Staff{
private String department;
private String researchArea;
 public Professor(String name,int age,int staffId,String department,String researchArea){
    super(name, age, staffId); 
    this.department = department;
    this.researchArea = researchArea;
 }
 public void DisplayProfDetails(){
    displayStaffDetails();
    System.out.println("The departement of professor is "+department+" and thier research area is "+researchArea+".\n");
 }
}
public class College {
    public static void main(String[] args) {
        new
    }
}
