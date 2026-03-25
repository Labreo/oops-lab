package Exp6;
import java.util.Scanner;

final class SecurityManager {
    private String username;
    private String password;

    public SecurityManager() {
        this.username = "guest";
        this.password = "guest";
    }

    public SecurityManager(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean validateUser() {
        return password.length() > 6;
    }

    @Override
    public String toString() {
        return "SecurityManager [Username: " + username + ", Password Length: " + password.length() + "]";
    }
}

public class Security {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Username: ");
        String username = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        SecurityManager sm = new SecurityManager(username, password);

        System.out.println("\n" + sm);

        if (sm.validateUser()) {
            System.out.println("Access Granted: Password meets the required length.");
        } else {
            System.out.println("Access Denied: Password must be more than 6 characters.");
        }

        sc.close();
    }
}