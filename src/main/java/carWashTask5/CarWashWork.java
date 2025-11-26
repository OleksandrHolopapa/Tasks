package carWashTask5;

import carWashTask5.enums.CarType;
import carWashTask5.enums.WashType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarWashWork {
    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>() {{
            add(new Car("Audi", CarType.PASSENGER_CAR));
            add(new Car("BMW", CarType.PASSENGER_CAR));
            add(new Car("Mercedes", CarType.PASSENGER_CAR));
            add(new Car("Lexus", CarType.PASSENGER_CAR));
            add(new Car("BMW", CarType.SUV));
            add(new Car("Mercedes", CarType.SUV));
        }};

        List<Client> clients = new ArrayList<>() {{
            add(new Client("Oleh", "0689514967", cars.get(0), cars.get(1)));
            add(new Client("Oleksandr", "0689514968", cars.get(2)));
            add(new Client("Oleksiy", "0689514969", cars.get(3)));
            add(new Client("Viktor", "0689514970", cars.get(4)));
        }};

        System.out.println("Clients Types on the start: ");
        clients.forEach(client -> System.out.printf("%-10s %-10s%n",client.getName(), client.getClientType()));

        //Отримуємо 101 мийку для клієнта Oleksandr та Oleksiy, щоб отримати статуси VIP,
        // заодно перевіримо знижку за кожну 10 мийку
        CarWash carWash = new CarWash();
        System.out.println("\nDISCOUNT DEMONSTRATION:");
        for (int i = 0; i < 101; i++) {
            long client3Wash = carWash.addTicket(clients.get(2), cars.get(3), WashType.STANDARD_WASH);
            long client2Wash = carWash.addTicket(clients.get(1), cars.get(2), WashType.FULL_SERVICE_WASH);
            carWash.startWash(client3Wash);
            carWash.startWash(client2Wash);
            carWash.finishWash(client3Wash);
            carWash.finishWash(client2Wash);
            if (i == 8 || i == 9) {
                System.out.println("Olexander and Oleksiy's car wash number: " + (i + 1));
                carWash.findTicketByNumber(client2Wash)
                        .ifPresent(ticket -> System.out.printf("Price of Oleksandr's ticket: %8s%n", ticket.getCost()));
                carWash.findTicketByNumber(client3Wash)
                        .ifPresent(ticket -> System.out.printf("Price of Oleksiy's ticket: %10s%n", ticket.getCost()));
            }
            carWash.carWashPayment(client2Wash);
            carWash.carWashPayment(client3Wash);
        }

        System.out.println("\nTypes of clients after 101 car washes at Olexiy and Olexander: ");
        clients.forEach(client -> System.out.printf("%-10s %-10s%n",client.getName(), client.getClientType()));

        //Додаємо 4 мийки. clients.get(0) Oleh миє нову машину,
        // яка при додаванні квитка до черги, буде додана йому в базі клієнтів
        System.out.println("\nOleh's cars before he bought the ticket: " + clients.get(0).getCars().stream()
                .map(Car::toString).collect(Collectors.joining(", ")));
        List<Long> washes = new ArrayList<>() {{
            add(carWash.addTicket(clients.get(0), cars.get(5), WashType.EXPRESS_WASH, WashType.WAX_APPLICATION));
            add(carWash.addTicket(clients.get(2), cars.get(3), WashType.FULL_SERVICE_WASH));
            add(carWash.addTicket(clients.get(3), cars.get(4), WashType.UNDERCARRIAGE_WASH, WashType.STANDARD_WASH));
            add(carWash.addTicket(clients.get(1), cars.get(2), WashType.UNDERCARRIAGE_WASH, WashType.STANDARD_WASH));
        }};
        System.out.println("Oleh's cars after he bought the ticket:  " + clients.get(0).getCars().stream()
                .map(Car::toString).collect(Collectors.joining(", ")));

        //Перевіряємо чергу, VIP-пи повинні бути на початку черги в порядку додавання
        System.out.println("\nQUEUE:");
        carWash.showTicketQueue();

        washes.forEach(carWash::startWash);
        washes.forEach(carWash::finishWash);

        //Отримуємо ціни за мийку
        System.out.println("\nPrices of ordered services:");
        carWash.getTicketQueue()
                .forEach(ticket -> System.out.printf("%-10s %-12s %-8s%n",
                        ticket.getWashStatus(), ticket.getClient().getName(), ticket.getCost()));

        //Оплачуємо
        washes.forEach(carWash::carWashPayment);

        //якщо все оплачено, то черга порожня
        if (carWash.getTicketQueue().isEmpty()) System.out.println("\nThe queue is empty");
    }
}