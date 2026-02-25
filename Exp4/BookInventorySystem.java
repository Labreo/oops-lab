package Exp4;

import java.util.Scanner;

class Book{
    private int bookid;
    private String title;
    private String author;
    private double price;
     public Book() {
        this.bookid = 0;
        this.author = "Unknown";
        this.title = "Unknown";
        this.price = 0.0;
    }
     public Book(int bookid, String title, String author,double price) {
        this.bookid = bookid;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public void setbookid(int bookid){
      this.bookid = bookid;
    }

    public void settitle(String title){
      this.title = title;
    }
    public void setauthor(String author){
      this.author = author;
    }
     public void setprice(double price){
      this.price = price;
    }
     public double getprice() {
        return price;
    }
    @Override
    public String toString() {
        return "Book Details:\n" +
               "Book id: " + bookid + "\n" +
               "Author: " + author + "\n" +
               "Title: " + title + "\n" +
               "Price: " + price + "\n";
    }

}

public class BookInventorySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of books: ");
        int n = sc.nextInt();
        sc.nextLine();
        Book[] books = new Book[n];
         for (int i = 0; i < n; i++) {
         System.out.println("\nEnter details for the Book No " + (i + 1));

            
            int id = i+1;
            

            System.out.print("Title: ");
            String title = sc.nextLine();
             System.out.print("Author: ");
            String author = sc.nextLine();
            System.out.print("Price: ");
            double price = sc.nextDouble();
            sc.nextLine();

            books[i] = new Book();
            books[i].setbookid(id);
            books[i].settitle(title);
            books[i].setprice(price);
            books[i].setauthor(author);
         }
        System.out.println("Enter the price above which we want to show the books:");
        double compprice = sc.nextDouble();
     System.out.println("Here are the books above "+compprice+":");
      for(int i=0;i<n;++i){
        
        if(compprice<books[i].getprice()){
         System.out.println(books[i]);
        }
      }
      sc.close();
    }

}
