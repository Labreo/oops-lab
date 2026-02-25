package Exp4;

import java.util.*;

class Product{
   private int productid;
   private String name;
   private int price;
   private int quantity;
   
   public Product(){
    this.productid = 0;  
    this.name = "Unknown";
    this.price = 0;
    this.quantity = 0;
   }
   
   public Product(int productid,String name,int price,int quantity){
      this.productid = productid;
      this.name = name;
      this.price = price;
      this.quantity = quantity;
   }
   
   public int getproductid(){
       return productid;
   }
   
   public String getname(){
       return name;
   }
   
   public int getprice(){
    return price;
   }
   
   public int getquantity(){
    return quantity;
   }
   
   public void setproductid(int productid){
       this.productid = productid;
   }
   
   public void setname(String name){
       this.name = name;
   }
   
   public void setprice(int price){
       this.price = price;
   }
   
   public void setquantity(int quantity){
       this.quantity = quantity;
   }
   
   @Override
   public String toString() {
       return "\n Product Details"+"\nProduct ID:"+productid+"\nName:"+name+"\nPrice:"+price+"\nQuantity:"+quantity;
   }
}

public class ProductBillingSystem {
    public static void main(String[] args) {
        int sum=0;
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter the amount of products to be entered:");
        int size = sc.nextInt();
        Product[] products = new Product[size];
        
        for(int i = 0;i<size;++i){
            System.out.println("\nEnter the details of the product "+(i+1)+":");
            System.out.println("\nEnter the product id of the product:");
            int productid = sc.nextInt();
            sc.nextLine();
            System.out.println("\nEnter the name of the product:");
            String name = sc.nextLine();
            System.out.println("\nEnter the price of the product:");
            int price = sc.nextInt();
            System.out.println("\nEnter the quantity of the product:");
            int quantity = sc.nextInt();
            
            if (i % 2 == 0) {
                products[i] = new Product(productid, name, price, quantity);
            } else {
                products[i] = new Product();
                products[i].setproductid(productid);
                products[i].setname(name);
                products[i].setprice(price);
                products[i].setquantity(quantity);
            }
        }
        
        for(Product product:products){
            System.out.println(product);
           sum+=product.getprice()*product.getquantity();
        }
        System.out.println("\nThe sum of all the product prices is:"+sum+".");
        
        sc.close();
    }
}