
public class exp1b {
    public static void makePattern() {
        int rows = 6;
        for (int i = 1; i < rows; i++) {     
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        makePattern();
    }
}
