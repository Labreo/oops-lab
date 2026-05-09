package Exp11;

import java.util.*;

public class Q3_ArrayList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.util.Scanner(System.in));
        ArrayList<Integer> numbers = new ArrayList<>();

        System.out.print("Enter how many integers you want to enter: ");
        int count = sc.nextInt();

        for (int i = 0; i < count; i++) {
            System.out.print("Enter integer " + (i + 1) + ": ");
            numbers.add(sc.nextInt());
        }

        System.out.println("\nBefore removal: " + numbers);

        Iterator<Integer> it = numbers.iterator();
        while (it.hasNext()) {
            Integer num = it.next();
            if (num % 2 == 0) {
                it.remove();
            }
        }

        System.out.println("After removing even numbers: " + numbers);
        
        sc.close();
    }
}