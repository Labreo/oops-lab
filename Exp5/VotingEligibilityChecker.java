package Exp5;

import java.util.Scanner;

class Voter{
 public String name;
    public int age;

    public Voter(int age,String name){
        this.age=age;
        this.name = name;
    }
    public int getAge(){
        return age;
    }
    public boolean checkEligibilty(){
        if(age>=18){
            return true;
        }else{
            return false;
        }
    }
}
class VoterEligibility{

    

    public static int countEligible(Voter[] votes){
        int num=0;
        for(Voter vote:votes){
            if(vote.checkEligibilty()==true){
              ++num;
            }
        }
       return num;
    }
}
public class VotingEligibilityChecker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
       
        System.out.println("Enter the No of voters you want to add:\n");
        int num = sc.nextInt();
        Voter[] votes = new Voter[num];
        sc.nextLine();
        for(int i =0;i<num;++i){
                   System.out.println("Enter the name you want to add:\n");
                   String name = sc.nextLine();
                   System.out.println("Enter the age of the voter:\n");
                   int age = sc.nextInt();
                   sc.nextLine();
                   votes[i] = new Voter(age, name);
        }
        int evotes = VoterEligibility.countEligible(votes);
        System.out.println("The no of eligible voters are:"+evotes);
        sc.close();
    }
}
