package tasksFrom6to8;

import java.util.Scanner;

public class SevensTask6 {
    public static void main(String[] args) {
        int num = 0;
        System.out.println("Enter your numbers:");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextInt()) {
                int userNum = scanner.nextInt();
                if (userNum % 7 == 0) break;
                if (Math.abs(userNum) % 10 == 7) num += userNum;
            } else {
                System.out.println("You should enter an integer number");
                scanner.next();
            }
        }
        System.out.println("Sum of numbers ending in 7 is: " + num);
        scanner.close();
    }
}