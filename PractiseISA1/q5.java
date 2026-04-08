import java.util.Scanner;

class Book{
    private int bookID,price;
    private String title,author;
   public Book(int bookID,int price,String title,String author){
       this.bookID = bookID;
       this.price = price;
       this.title = title;
       this.author = author;
   }
   public int getPrice(){
    return price;
   }
@Override
public String toString(){
    return "The book ID is "+bookID+".\nThe price is "+price+".\nThe title is "+title+"\n.The author is "+author+".\n";

}}
public class q5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no of books:");
        int n = sc.nextInt();
        Book[] books = new Book[n];
        for(int i = 0;i<n;++i){
         System.out.println("\nEnter the book ID:");
        int bookID = sc.nextInt();
         System.out.println("\nEnter the price:");
        int price = sc.nextInt();  
        sc.nextLine();        
         System.out.println("\nEnter the title:");
        String title = sc.nextLine(); 
         System.out.println("\nEnter the author:");
        String author = sc.nextLine();    
        books[i]=new Book(bookID, price, title, author);                 
        }
     System.out.println("\nEnter the lower limit price whose books you want to display:");
        int p = sc.nextInt();
        for(Book b:books){
            if(b.getPrice()>=p){
               System.out.println(b);
            }
        }
       sc.close();
    }
}

/*Book Inventory System: Create a Book class with private attributes:
book ID, title, author, and price. Create an array of book objects and
display all books whose price is above a given value. */