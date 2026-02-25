package Exp4;

import java.util.Scanner;

class BankAccount{
   private int accountno;
   private String name;
   private double balance;
      public BankAccount() {
        this.accountno = 0;
        this.name = "Unknown";
        this.balance = 0.0;
      
    }
    public BankAccount(int accountno,String name,double balance){
        this.accountno = accountno;
        this.name = name;
        this.balance = balance;
    }
    public void depoistamount(double balance,double depoistprice){
        this.balance+=depoistprice;
    }
      public void withdrawamount(double balance,double depoistprice){
        this.balance-=depoistprice;
    }
    public void  setname(String name){
      this.name = name;
    }
    public void  setaccountno(int accountno){
      this.accountno = accountno;
    }
    public void  setbalance(int balance){
      this.balance = balance;
    }

}
public class BankAccountDetails {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        BankAccount[] bankAccounts = new BankAccount[n];
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for the Account: " + (i + 1));
            System.out.println("\nEnter the Account No:");
            int accountno = sc.nextInt();
             System.out.println("\nEnter the name:");
            String name = sc.nextLine();
            System.out.println("\nEnter the balance:");
            double balance = sc.nextDouble();
         sc.nextLine();
         bankAccounts[i] = new BankAccount(accountno, name, balance);
        }
    while(true){
     System.out.println("Press 0 if you want to deposit money to a specific account.Press 1 if you want to withdraw money from a specific account.Press 2 to exit:");
     int input = sc.nextInt();
     if(input == 0){
          System.out.println("\nEnter the Account No:");
         int accountno = sc.nextInt();
                  System.out.println("\nEnter the amount to deposit:");
         int depoaccount = sc.nextDouble();
     }
        }
        sc.close();
    }
}
