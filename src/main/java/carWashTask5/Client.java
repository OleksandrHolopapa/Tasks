package carWashTask5;

import carWashTask5.enums.ClientType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Client {
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String phone;
    @Getter
    private final List<Car> cars;
    @Setter
    @Getter
    private ClientType clientType;
    @Getter
    private int washCount = 0;

    Client(String name, String phone, Car... cars) {
        this.name = name;
        this.phone = phone;
        this.cars = new ArrayList<>(List.of(cars));
        this.clientType = ClientType.STANDARD;
    }

    public void increaseWashCount() {
        ++this.washCount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(name, client.name) && Objects.equals(phone, client.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone);
    }

    @Override
    public String toString() {
        return name + ", " + phone + ", " + clientType + ", washCount = " + washCount + ", "
                + cars.stream().map(Car::toString).collect(Collectors.joining(", "));
    }
}