package cafeTask3;

import lombok.Data;

@Data
public class CoffeeOrder {
    private long id;
    private String email;
    private String coffeeName;
    private CoffeeSize coffeeSize;
    private int price;
    private OrderStatus orderStatus;

    public CoffeeOrder(String email, String coffeeName, CoffeeSize coffeeSize, int price) {
        this.email = email;
        this.coffeeName = coffeeName;
        this.coffeeSize = coffeeSize;
        this.price = price;
        this.orderStatus = OrderStatus.PENDING;
    }

    enum  OrderStatus {
        PENDING, COMPLETED, CANCELLED
    }

    enum CoffeeSize {
        SMALL, MEDIUM, LARGE
    }
}