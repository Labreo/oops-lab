import java.util.Scanner;

public class SumPrincipalDiagonalMatrix {
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the amount of rows and columns for the square matrix:");
       int r = scanner.nextInt();
        int[][] m1 = new int[r][r];
        int sum=0;
         for(int i = 0;i<r;++i){
        for(int j=0;j<r;++j){
            System.out.println("Enter the value for "+i+","+j+" in matrix:");
            m1[i][j]=scanner.nextInt();
            if(i==j){
                sum+=m1[i][j];
            }
        }

       }
               System.out.println("The sum of the principal diagonal of the matrix is "+sum+".");
    }
}
