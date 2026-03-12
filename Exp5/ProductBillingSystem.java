package Exp5;

import java.util.*;
class ProductBill{
       private int productid;
   private String name;
   private int price;
   private int quantity;
   
   public ProductBill(){
    this.productid = 0;  
    this.name = "Unknown";
    this.price = 0;
    this.quantity = 0;
   }
   
   public ProductBill(int productid,String name,int price,int quantity){
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
   public int calculateTotalPrice(){
    return price*quantity;
   }
   public static int calculateStoreRevenue(ProductBill[] products){
    int total=0;
    for(ProductBill x:products){
        total=total+x.calculateTotalPrice();
    }
    return total;
   }

}
public class ProductBillingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
       int sum=0;
        System.out.println("\nEnter the amount of products to be entered:");
        int size = sc.nextInt();
        ProductBill[] products = new ProductBill[size];
        
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
                products[i] = new ProductBill(productid, name, price, quantity);
            } else {
                products[i] = new ProductBill();
                products[i].setproductid(productid);
                products[i].setname(name);
                products[i].setprice(price);
                products[i].setquantity(quantity);
            }
        }
        sum = calculateStoreRevenue(products);
        sc.close();
    }
}
