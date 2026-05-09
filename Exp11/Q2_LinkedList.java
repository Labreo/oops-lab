package Exp11;

import java.util.*;

class Book {
    private String title;
    private String author;
    private String isbn;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return String.format("[Title: %s, Author: %s, ISBN: %s]", title, author, isbn);
    }
}

public class Q2_LinkedList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedList<Book> reservationQueue = new LinkedList<>();
        boolean running = true;

        while (running) {
            System.out.println("\n--- Library Reservation System ---");
            System.out.println("1. Add Book to Queue");
            System.out.println("2. Serve (Remove) Next Book");
            System.out.println("3. Peek Next Book");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    System.out.print("Enter ISBN: ");
                    String isbn = sc.nextLine();
                    reservationQueue.addLast(new Book(title, author, isbn));
                    break;

                case 2:
                    if (!reservationQueue.isEmpty()) {
                        Book served = reservationQueue.removeFirst();
                        System.out.println("Served: " + served);
                    } else {
                        System.out.println("Queue is empty.");
                    }
                    break;

                case 3:
                    if (!reservationQueue.isEmpty()) {
                        System.out.println("Next in Queue: " + reservationQueue.peekFirst());
                    } else {
                        System.out.println("Queue is empty.");
                    }
                    break;

                case 4:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

            printQueue(reservationQueue);
        }
        sc.close();
    }

    private static void printQueue(LinkedList<Book> queue) {
        System.out.println("Current Reservation Queue:");
        if (queue.isEmpty()) {
            System.out.println("<Empty>");
        } else {
            for (Book b : queue) {
                System.out.println(b);
            }
        }
    }
}