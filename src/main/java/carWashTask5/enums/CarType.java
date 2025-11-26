package carWashTask5.enums;

import lombok.Getter;

@Getter
public enum CarType {
    PASSENGER_CAR(1.0), // Sedan, Coupe, Hatchback, Station Wagon, Sports Car, Convertible
    SUV(1.2),           // Crossover, SUV (Sport Utility Vehicle)
    MINIVAN_VAN(1.3),   // Minivan, Van
    PICKUP_TRUCK(1.5);   // Pickup and other small trucks

    private final double coefficient;

    CarType(double coefficient) {
        this.coefficient = coefficient;
    }
}