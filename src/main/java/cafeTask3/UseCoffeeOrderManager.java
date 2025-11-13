package cafeTask3;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class UseCoffeeOrderManager {
    public static void main(String[] args) throws InterruptedException {
        CoffeeOrderManager coffeeOrderManager = new CoffeeOrderManager();
        coffeeOrderManager.addOrder(new CoffeeOrder("user1@gmail.com", "Latte", CoffeeOrder.CoffeeSize.MEDIUM, 500));
        Thread.sleep(10);
        coffeeOrderManager.addOrder(new CoffeeOrder("user2@gmail.com", "Espresso", CoffeeOrder.CoffeeSize.LARGE, 750));
        Thread.sleep(10);
        coffeeOrderManager.addOrder(new CoffeeOrder("user3@gmail.com", "Cappuccino", CoffeeOrder.CoffeeSize.SMALL, 400));
        Thread.sleep(10);
        coffeeOrderManager.addOrder(new CoffeeOrder("user4@gmail.com", "Latte", CoffeeOrder.CoffeeSize.SMALL, 400));
        Thread.sleep(10);
        coffeeOrderManager.addOrder(new CoffeeOrder("user5@gmail.com", "Espresso", CoffeeOrder.CoffeeSize.LARGE, 750));
        Thread.sleep(10);
        coffeeOrderManager.addOrder(new CoffeeOrder("user6@gmail.com", "Cappuccino", CoffeeOrder.CoffeeSize.SMALL, 400));
        Thread.sleep(10);
        coffeeOrderManager.addOrder(new CoffeeOrder("user9@gmail.com", "Americano", CoffeeOrder.CoffeeSize.SMALL, 400));
        Thread.sleep(10);
        coffeeOrderManager.addOrder(new CoffeeOrder("user8@gmail.com", "Espresso", CoffeeOrder.CoffeeSize.LARGE, 750));
        Thread.sleep(10);
        coffeeOrderManager.addOrder(new CoffeeOrder("user9@gmail.com", "Doppio", CoffeeOrder.CoffeeSize.SMALL, 400));
        Thread.sleep(10);
        coffeeOrderManager.addOrder(new CoffeeOrder("user10@gmail.com", "Macchiato", CoffeeOrder.CoffeeSize.SMALL, 400));
        Thread.sleep(10);
        coffeeOrderManager.addOrder(new CoffeeOrder("user9@gmail.com", "Doppio", CoffeeOrder.CoffeeSize.SMALL, 400));
        Thread.sleep(10);
        coffeeOrderManager.addOrder(new CoffeeOrder("user10@gmail.com", "Macchiato", CoffeeOrder.CoffeeSize.SMALL, 400));

        System.out.println(coffeeOrderManager);
        System.out.println("Top drinks today:");
        List<String> topDrinksToday = coffeeOrderManager.getTopDrinksToday();
        topDrinksToday.forEach(System.out::println);

        coffeeOrderManager.completeOrder(1);
        coffeeOrderManager.completeOrder(2);
        coffeeOrderManager.completeOrder(3);
        coffeeOrderManager.completeOrder(4);
        coffeeOrderManager.completeOrder(5);
        coffeeOrderManager.completeOrder(6);
        coffeeOrderManager.completeOrder(7);
        coffeeOrderManager.completeOrder(8);
        coffeeOrderManager.completeOrder(9);
        coffeeOrderManager.completeOrder(10);
        coffeeOrderManager.completeOrder(11);
        coffeeOrderManager.completeOrder(12);

        System.out.println("Loyal customers:");
        Set<String> loyalCustomers = coffeeOrderManager.getLoyalCustomers();
        loyalCustomers.forEach(System.out::println);

        System.out.println("Peak hours stats:");
        Map<String, Long> peakHoursStats = coffeeOrderManager.getPeakHoursStats();
        peakHoursStats.forEach((hour, count) -> System.out.println(hour + ": " + count));

        System.out.println("Average order price:");
        double averageOrderValue = coffeeOrderManager.getAverageOrderValue();
        System.out.printf("%.2f грн", averageOrderValue/100);
    }
}