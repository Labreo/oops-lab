import java.util.Scanner;

public class NoOfOccurance {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size, searchElement, position = -1;
        boolean found = false;
        int count=0;

        System.out.print("Enter the number of elements in the array: ");
        size = scanner.nextInt();
        int[] array = new int[size];

        System.out.println("Enter " + size + " elements:");
        for (int i = 0; i < size; i++) {
            System.out.print("Element " + (i + 1) + ": ");
            array[i] = scanner.nextInt();
        }
        System.out.print("\nEnter the element to find the number of occurances for: ");
        searchElement = scanner.nextInt();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == searchElement) {
                position = i; 
                found = true;
                count++;
                
            }
        }

        if (found) {
            System.out.println(searchElement + " has occured "+count+" times in the array. ");
        } else {
            System.out.println(searchElement + " is not present in the array.");
        }

        scanner.close();
    }
}