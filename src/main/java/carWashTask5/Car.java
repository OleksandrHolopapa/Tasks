package carWashTask5;

import carWashTask5.enums.CarType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Car {
    private String model;
    private CarType carType;

    @Override
    public String toString() {
        return model + " " + carType;
    }
}