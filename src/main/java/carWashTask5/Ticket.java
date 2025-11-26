package carWashTask5;

import carWashTask5.enums.WashStatus;
import carWashTask5.enums.WashType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@EqualsAndHashCode(of = {"ticketNumber", "client", "car", "startTime", "washTypes"})
public class Ticket {
    @Getter
    @Setter
    private long ticketNumber;
    @Getter
    @Setter
    private Client client;
    @Getter
    @Setter
    private Car car;
    @Getter
    private Set<WashType> washTypes;
    @Getter
    private double cost;
    @Getter
    private LocalDateTime startTime;
    @Getter
    @Setter
    private LocalDateTime finishTime;
    @Getter
    @Setter
    private WashStatus washStatus;

    Ticket(Client client, Car car, LocalDateTime startTime, WashType... washTypes) {
        this.client = client;
        this.car = car;
        this.washTypes = new HashSet<>(Arrays.asList(washTypes));
        this.startTime = startTime;
        this.cost = calculateCost();
        this.finishTime = calculateFinishTime();
        this.washStatus = WashStatus.NEW;
    }

    private double calculateCost() {
        int price = washTypes.stream()
                .mapToInt(WashType::getBasePrice)
                .sum();
        return price * car.getCarType().getCoefficient();
    }

    private LocalDateTime calculateFinishTime() {
        if (this.startTime == null) return null;
        int duration = washTypes.stream()
                .mapToInt(WashType::getEstimatedDurationMinutes)
                .sum();
        return this.startTime.plusMinutes(duration);
    }

    public void setWashTypes(WashType... washTypes) {
        this.washTypes = new HashSet<>(Arrays.asList(washTypes));
        this.cost = calculateCost();
        this.finishTime = calculateFinishTime();
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        this.finishTime = calculateFinishTime();
    }

    public void addWashTypes(WashType... washTypes) {
        this.washTypes.addAll(Arrays.asList(washTypes));
        this.cost = calculateCost();
        this.finishTime = calculateFinishTime();
    }

    public void deleteWashTypes(WashType... washTypes) {
        this.washTypes.removeAll(Arrays.asList(washTypes));
        this.cost = calculateCost();
        this.finishTime = calculateFinishTime();
    }

    public void applyDiscount(int discountPercentage) {
        this.cost *= 1 - discountPercentage / 100.0;
    }

    @Override
    public String toString() {
        return "Ticket " + ticketNumber + "\n" +
                "Client: " + client + "\n" +
                "Car: " + car + "\n" +
                "Wash types: " + washTypes + "\n" +
                "Cost: " + cost + "\n" +
                "Start time: " + startTime + "\n" +
                "Finish time: " + finishTime;
    }
}