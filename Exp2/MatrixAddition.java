
import java.util.*;


public class MatrixAddition {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the amount of rows:");
       int r = scanner.nextInt();
     System.out.print("Enter the amount of columns:");
       int c = scanner.nextInt();
    
       int[][] m1 = new int[r][c];
       int[][] m2 = new int[r][c];
       int[][] sum = new int[r][c];
       for(int i = 0;i<r;++i){
        for(int j=0;j<c;++j){
            System.out.println("Enter the value for "+i+","+j+" in matrix 1:");
            m1[i][j]=scanner.nextInt();
        }
       }
        for(int i = 0;i<r;++i){
        for(int j=0;j<c;++j){
            System.out.println("Enter the value for "+i+","+j+" in matrix 2:");
            m2[i][j]=scanner.nextInt();
        }
       }
       System.out.println("The sum of both the matrixes are:");
        for(int i = 0;i<r;++i){
        for(int j=0;j<c;++j){
            sum[i][j]=m1[i][j]+m2[i][j];
            System.out.print(sum[i][j]+" ");
        }
        System.out.println();
       }
    }
}
