package tasksFrom6to8;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TenNumbersTask7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Double> numbers = new ArrayList<>();
        System.out.println("Enter 10 numbers:");
        for (int i = 0; i < 10; i++) {
            if (scanner.hasNextDouble()) {
                numbers.add(scanner.nextDouble());
            } else {
                System.out.println("You should enter only numbers");
                scanner.next();
                i--;
            }
        }
        numbers.stream().max(Double::compareTo).ifPresent(max -> System.out.println("Max number is: " + max));
    }
}