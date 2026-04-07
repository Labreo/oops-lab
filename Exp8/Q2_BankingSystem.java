package Exp8;

import java.util.Scanner;

abstract class BankAccount {
    protected double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public abstract double calculateInterest();

    public final void displayAccountType(String accountType) {
        System.out.println("Account Type: " + accountType);
        System.out.println("Current Balance: $" + balance);
    }
}

class SavingsAccount extends BankAccount {
    private double interestRate = 0.05; 

    public SavingsAccount(double balance) {
        super(balance);
    }

    @Override
    public double calculateInterest() {
        return balance * interestRate;
    }
}

class CurrentAccount extends BankAccount {
    private double interestRate = 0.01; 

    public CurrentAccount(double balance) {
        super(balance);
    }

    @Override
    public double calculateInterest() {
        return balance * interestRate;
    }
}

public class Q2_BankingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Q2: Banking System ---");
        
        System.out.print("Enter initial balance for Savings Account: ");
        double savingsBal = scanner.nextDouble();
        
        System.out.print("Enter initial balance for Current Account: ");
        double currentBal = scanner.nextDouble();

        BankAccount savings = new SavingsAccount(savingsBal);
        BankAccount current = new CurrentAccount(currentBal);

        System.out.println("\n--- Account Details ---");
        
        savings.displayAccountType("Savings Account");
        System.out.println("Calculated Interest: $" + savings.calculateInterest());

        System.out.println();

        current.displayAccountType("Current Account");
        System.out.println("Calculated Interest: $" + current.calculateInterest());

        scanner.close();
    }
}