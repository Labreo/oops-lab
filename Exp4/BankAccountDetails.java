package Exp4;

import java.util.Scanner;

class BankAccount {
    private int accountno;
    private String name;
    private double balance;

    public BankAccount() {
        this.accountno = 0;
        this.name = "Unknown";
        this.balance = 0.0;

    }

    public BankAccount(int accountno, String name, double balance) {
        this.accountno = accountno;
        this.name = name;
        this.balance = balance;
    }

    public void depoistamount(double depoistprice) {
        this.balance += depoistprice;
    }

    public void withdrawamount(double depoistprice) {
        this.balance -= depoistprice;
    }

    public void setname(String name) {
        this.name = name;
    }

    public void setaccountno(int accountno) {
        this.accountno = accountno;
    }

    public void setbalance(double balance) {
        this.balance = balance;
    }

    public int getaccountno() {
        return accountno;
    }

    public String getname() {
        return name;
    }

    public double getbalance() {
        return balance;
    }

    @Override
    public String toString() {
            return "Bank Details:\n" +
               "Account No: " + accountno + "\n" +
               "Name: " + name + "\n" +
               "Balance: " + balance + "\n";
    }
}

public class BankAccountDetails {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter the amount of accounts to be created:");
        int n = sc.nextInt();
        BankAccount[] bankAccounts = new BankAccount[n];
        
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for the Account: " + (i + 1));
            System.out.println("\nEnter the Account No:");
            int accountno = sc.nextInt();
            sc.nextLine();
            System.out.println("\nEnter the name:");
            String name = sc.nextLine();
            System.out.println("\nEnter the balance:");
            double balance = sc.nextDouble();
            sc.nextLine();
            
            if (i % 2 == 0) {
                bankAccounts[i] = new BankAccount(accountno, name, balance);
            } else {
                bankAccounts[i] = new BankAccount();
                bankAccounts[i].setaccountno(accountno);
                bankAccounts[i].setname(name);
                bankAccounts[i].setbalance(balance);
            }
        }
        
        while (true) {
            System.out.println(
                    "Press 0 if you want to deposit money to a specific account.Press 1 if you want to withdraw money from a specific account.Press 2 to exit:");
            int input = sc.nextInt();
            if (input == 0) {
                System.out.println("\nEnter the Account No:");
                int accountno = sc.nextInt();
                System.out.println("\nEnter the amount to deposit:");
                double depoaccount = sc.nextDouble();
                for (BankAccount account : bankAccounts) {
                    int a = account.getaccountno();
                    if (a == accountno) {
                        account.depoistamount(depoaccount);
                        break;
                    }
                }

            }
            if (input == 1) {
                System.out.println("\nEnter the Account No:");
                int accountno = sc.nextInt();
                System.out.println("\nEnter the amount to withdraw:");
                double witthdraw = sc.nextDouble();
                for (BankAccount account : bankAccounts) {
                    int a = account.getaccountno();
                    if (a == accountno) {
                        account.withdrawamount(witthdraw);
                        break;
                    }
                }
            }
            if (input == 2) {
                break;
            }
        }

        System.out.println("\nBank Account Details");
        for (BankAccount account : bankAccounts) {
            System.out.println(account);
        }

        sc.close();
    }
}