import java.util.*;

public class exp1c {
    public static void b2d(String s){
      int ans=0,i,p=0;
      for(i=s.length()-1;i>=0;i--){
         if (s.charAt(i) == '1') {
        ans+=Math.pow(2,p);}
        p++;
      }
      System.out.println();
       System.out.println(ans);
    }
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the binary no to convert: ");
        String B = scanner.nextLine();
       scanner.close();
      b2d(B);
    }
}
