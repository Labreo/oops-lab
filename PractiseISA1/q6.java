import java.util.*;

public class q6 {
    public static void main(String args[]){
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the string");
            String in = sc.nextLine().toLowerCase();
            int count =0;
            Boolean flag = false;
            for(char s:in.toCharArray()){
                count=0;
                for(char t:in.toCharArray()){
                   if(s==t){
                    ++count;
                   }

                }
                if(count==1){
                    System.out.println("\nThe char which is repeated once is "+s);
                    flag=true;
                    break;
    
                    
                }else{
                    continue;
                }
            }
            if(flag==false){
            System.out.println("\nAll the chars are repeated.");}
            sc.close();

    }
}
