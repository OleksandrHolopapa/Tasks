package tasksFrom6to8;

import java.util.Scanner;

public class LoginAndPasswordTask8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        while (true) {
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();
            System.out.print("Enter your password one more time: ");
            String confirmPassword = scanner.nextLine();
            if (password.equals(confirmPassword)) {
                System.out.println("Success");
                break;
            } else {
                System.out.println("Passwords do not match");
                System.out.println("Try one more time");
            }
        }
    }
}