package Exp12;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Book {
    private int bookid;
    private String title;
    private String author;
    private double price;

    public Book() {
        this.bookid = 0;
        this.title = "Unknown";
        this.author = "Unknown";
        this.price = 0.0;
    }

    public Book(int bookid, String title, String author, double price) {
        this.bookid = bookid;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public void setbookid(int bookid) {
        this.bookid = bookid;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public void setauthor(String author) {
        this.author = author;
    }

    public void setprice(double price) {
        this.price = price;
    }

    public int getbookid() {
        return bookid;
    }

    public String gettitle() {
        return title;
    }

    public String getauthor() {
        return author;
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
        String inputFileName = sc.nextLine();
        //input3.txt
        String outputFileName = "output2.txt";
        File inputFile = new File(inputFileName);

        if (!inputFile.exists()) {
            System.out.println("Error: The file '" + inputFileName + "' does not exist.");
            return;
        }
        if (!inputFile.canRead()) {
            System.out.println("Error: Do not have read permissions for '" + inputFileName + "'.");
            return;
        }

        
        System.out.print("Enter the price above which we want to show the books: ");
        double compprice = sc.nextDouble();
        sc.close();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {

            String line = reader.readLine();
            if (line == null) {
                System.out.println("Input file is empty.");
                return;
            }

            int n = Integer.parseInt(line.trim());
            Book[] books = new Book[n];

            for (int i = 0; i < n; i++) {
                int id = Integer.parseInt(reader.readLine().trim());
                String title = reader.readLine().trim();
                String author = reader.readLine().trim();
                double price = Double.parseDouble(reader.readLine().trim());

                if (i % 2 == 0) {
                    books[i] = new Book(id, title, author, price);
                } else {
                    books[i] = new Book();
                    books[i].setbookid(id);
                    books[i].settitle(title);
                    books[i].setauthor(author);
                    books[i].setprice(price);
                }
            }

            writer.write("Here are the books above " + compprice + ":\n");
            writer.write("========================\n");

            boolean found = false;
            for (int i = 0; i < n; i++) {
                if (books[i].getprice() > compprice) {
                    writer.write(books[i].toString());
                    writer.write("------------------------\n");
                    found = true;
                }
            }

            if (!found) {
                writer.write("No books found above the given price.\n");
            }

            System.out.println("Success! Filtered books have been written to " + outputFileName);

        } catch (IOException e) {
            System.out.println("An I/O Error occurred: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Data formatting error in input file: " + e.getMessage());
        }
    }
}