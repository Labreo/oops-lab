package Exp5;

import java.util.Scanner;

class Library{
    public static int totalBooksAvailable;
    public Library(int totalBooksAvailable){
        Exp5.Library.totalBooksAvailable = totalBooksAvailable;
    }
    public void issueBook(){
        --totalBooksAvailable;
    }
    public void returnBook(){
        ++totalBooksAvailable;
    }
     public static void displayTotalBooks(){
        System.out.println("The total no of books available is "+totalBooksAvailable+".\n");
    }
}
public class LibraryManagement {
    public static void main(String[] args) {
     Scanner sc = new Scanner(System.in);
     System.out.println("Enter the number of books currently available in the library:");
     int books = sc.nextInt();
     Library lib = new Library(books);
    while(true){
        System.out.println("\nEnter 0 if you want to issue a book,Enter 1 if you want to return a book,Enter 2 to see total no of books,Enter 3 to exit the loop:");
        int input = sc.nextInt();
        if(input==0){
            System.out.println("The book has been issued.\n");
            lib.issueBook();
        }
        if(input==1){
            System.out.println("The book has been returned.\n");
            lib.returnBook();
        }
                        if(input==2){
           Library.displayTotalBooks();
        }
        if(input==3){
            break;
        }
    }
    sc.close();
    }
}
