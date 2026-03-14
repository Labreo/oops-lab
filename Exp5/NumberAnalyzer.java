package Exp5;

import java.util.Scanner;

class Number_Analyzer {
    public int num;

    public Number_Analyzer(int num) {
        this.num = num;
    }

    public boolean checkPrime() {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); ++i) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public long findFactorial(int num) {
        long sum = 1;
        int i = 1;
        while (i <= num) {
            sum = sum * i;
            ++i;
        }
        return sum;
    }

    public static int countDigits(int num) {
        int i = 0;
        while (num > 0) {
            num = num / 10;
            ++i;
        }
        return i;
    }
}

public class NumberAnalyzer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number:");
        int num = sc.nextInt();
        Number_Analyzer na = new Number_Analyzer(num);
        while (true) {
            System.out.println(
                    "Press 0 to check prime\nPress 1 to find factorial\nPress 2 to count digits\nPress 3 to exit:");
            int input = sc.nextInt();
            if (input == 0) {
                System.out.println("Is prime: " + na.checkPrime());
            } else if (input == 1) {
                System.out.println("Factorial: " + na.findFactorial(num));
            } else if (input == 2) {
                System.out.println("Digit count: " + Number_Analyzer.countDigits(num));
            } else if (input == 3) {
                break;
            }
        }
        sc.close();
    }
}