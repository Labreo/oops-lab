package Exp9;

import java.util.Scanner;

interface Taxable {
    default double calculateTax(double balance) {
        if (balance > 100000) {
            return 0.025 * (balance - 100000);
        } else {
            return 0;
        }
    }
}

interface Auditable {
    default String generateAuditScore(int transactions) {
        if (transactions > 100) {
            return "HIGH";
        } else {
            return "LOW";
        }
    }
}

class SavingsAccount implements Auditable, Taxable {
    public double balance;
    public int transactions;

    public SavingsAccount() {
        this.balance = 0.0;
        this.transactions = 0;
    }

    public SavingsAccount(double balance, int transactions) {
        this.balance = balance;
        this.transactions = transactions;
    }

    @Override
    public double calculateTax(double balance) {
        if (balance > 100000) {
            return 0.018 * (balance - 100000);
        } else {
            return 0;
        }
    }

    public void generateHealthReport() {
        double defaultTax = Taxable.super.calculateTax(balance);
        double appliedTax = this.calculateTax(balance);
        double netBalance = balance - appliedTax;
        String auditScore = generateAuditScore(transactions);

        System.out.println("Default Computed Tax (2.5%): " + defaultTax);
        System.out.println("Applied Savings Tax (1.8%): " + appliedTax);
        System.out.println("Net Balance after tax: " + netBalance);
        System.out.println("Audit Score: " + auditScore);
    }

    @Override
    public String toString() {
        return "SavingsAccount [Balance: " + balance + ", Transactions: " + transactions + "]";
    }
}

public class Q1_BankingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter account balance: ");
        double balance = scanner.nextDouble();

        System.out.print("Enter number of transactions: ");
        int transactions = scanner.nextInt();

        SavingsAccount account = new SavingsAccount(balance, transactions);

        System.out.println("\n--- Account Details ---");
        System.out.println(account.toString());

        System.out.println("\n--- Account Health Report ---");
        account.generateHealthReport();

        scanner.close();
    }
}