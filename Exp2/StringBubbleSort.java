import java.util.Scanner;
public class StringBubbleSort {
    public static void bubbleSort(String[] arr) {
        int n = arr.length;
        String temp;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) { swapped = false;
            for (int j = 0; j < n - i - 1; j++) { 
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;}}
            if (!swapped)            break;   } }
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
        int size;
        String input;
        System.out.print("Enter the number of elements in the array: ");
        size = scanner.nextInt();
        ++size;
        String[] arr = new String[size];
          System.out.print("Enter the strings: "); 
        for(int i=0;i<size;++i){ input = scanner.nextLine();
          arr[i]=input; }
        System.out.println("Unsorted array: ");
        for (String s : arr) {
            System.out.print(s + " ");}
        bubbleSort(arr);
        System.out.println("\nSorted array: ");
        for (String s : arr) {
            System.out.print(s + " "); }}}
