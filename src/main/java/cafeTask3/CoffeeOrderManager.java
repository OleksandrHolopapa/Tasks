package cafeTask3;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class CoffeeOrderManager {
    private final Map<LocalDateTime, CoffeeOrder> coffeeOrderList = new TreeMap<>();
    private long orderId = 0;

    public void addOrder(CoffeeOrder order) {
        if(botWarning(order)) {
            System.out.println("Customer "+order.getEmail()+" can be a bot");
        }
        order.setId(++orderId);
        coffeeOrderList.put(LocalDateTime.now(), order);
    }

    private boolean botWarning(CoffeeOrder order) {
        return coffeeOrderList.entrySet().stream()
                .filter(entry -> entry.getValue().getEmail().equals(order.getEmail()))
                .filter(entry -> entry.getKey().toLocalTime().isAfter(LocalTime.now().minusMinutes(10)))
                .count()>5;
    }

    public void cancelOrder(long orderId) {
        coffeeOrderList.values().stream()
                .filter(order -> order.getOrderStatus() == CoffeeOrder.OrderStatus.PENDING)
                .filter(order -> order.getId() == orderId)
                .findFirst()
                .ifPresent(coffeeOrder -> coffeeOrder.setOrderStatus(CoffeeOrder.OrderStatus.CANCELLED));
    }

    public void completeOrder(long orderId) {
        coffeeOrderList.values().stream()
                .filter(order -> order.getId() == orderId)
                .findFirst()
                .ifPresent(coffeeOrder -> coffeeOrder.setOrderStatus(CoffeeOrder.OrderStatus.COMPLETED));
    }

    public List<String> getTopDrinksToday() {
        return coffeeOrderList.entrySet().stream()
                .filter(entry -> entry.getKey().toLocalDate().isEqual(LocalDate.now()))
                .map(Map.Entry::getValue)
                .collect(Collectors.groupingBy(CoffeeOrder::getCoffeeName, Collectors.counting()))
                .entrySet().stream()
                .sorted((k, v) -> v.getValue().compareTo(k.getValue()))
                .limit(5)
                .map(Map.Entry::getKey)
                .toList();
    }

    public Set<String> getLoyalCustomers() {
        return coffeeOrderList.entrySet().stream()
                .filter(entry -> entry.getValue().getOrderStatus() == CoffeeOrder.OrderStatus.COMPLETED)
                .filter(entry -> entry.getKey().toLocalDate().isAfter(LocalDate.now().minusWeeks(1)))
                .map(Map.Entry::getValue)
                .collect(Collectors.groupingBy(CoffeeOrder::getEmail, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() >= 3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    public Map<String, Long> getPeakHoursStats() {
        Map<String, Long> peakHoursStats = new HashMap<>();
        peakHoursStats.put("Morning", getOrderCountInPeekHours(7, 11));
        peakHoursStats.put("Afternoon", getOrderCountInPeekHours(11, 15));
        peakHoursStats.put("Evening", getOrderCountInPeekHours(15, 22));
        return peakHoursStats;
    }

    private long getOrderCountInPeekHours(int startHour, int finishHour) {
        return coffeeOrderList.entrySet().stream()
                .filter(entry -> entry.getKey().toLocalTime().getHour() >= startHour && entry.getKey().toLocalTime().getHour() < finishHour)
                .count();
    }

    public double getAverageOrderValue() {
        return coffeeOrderList.entrySet().stream()
                .filter(entry -> entry.getKey().toLocalDate().isAfter(LocalDate.now().minusDays(1)))
                .map(Map.Entry::getValue)
                .mapToDouble(CoffeeOrder::getPrice)
                .average()
                .orElse(0);
    }

    public void exportToCsv() {
        getLoyalCustomers().forEach(System.out::println);
    }

    @Override
    public String toString() {
        return coffeeOrderList.entrySet().stream().
                map(entry -> entry.getKey().toLocalDate()+" "+entry.getKey().toLocalTime() + " : " + entry.getValue()).collect(Collectors.joining("\n"));
    }
}