package carWashTask5;

import carWashTask5.enums.ClientType;
import carWashTask5.enums.WashStatus;
import carWashTask5.enums.WashType;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CarWash {
    private static long ticketNumber = 0;
    @Getter
    private final List<Ticket> ticketQueue = new ArrayList<>();
    private final ClientManager clientManager = new ClientManager();

    public long addTicket(Client client, Car car, WashType... washTypes) {
        Ticket ticket = new Ticket(client, car, null, washTypes);
        ticket.setTicketNumber(++ticketNumber);
        updateClientDataBase(client, car);
        addTicketToQueue(client, ticket);
        return ticketNumber;
    }

    public Optional<Ticket> findTicketByNumber(long ticketNumber) {
        return ticketQueue.stream()
                .filter(ticket -> ticket.getTicketNumber() == ticketNumber)
                .findFirst();
    }

    public void startWash(long ticketNumber) {
        ticketQueue.stream()
                .filter(ticket -> ticket.getTicketNumber() == ticketNumber)
                .findFirst().ifPresent(ticket -> {
                    ticket.setStartTime(LocalDateTime.now());
                    ticket.getClient().increaseWashCount();
                    tryToUseDiscount(ticket);
                    ticket.setWashStatus(WashStatus.IN_PROGRESS);
                });
    }

    public void finishWash(long ticketNumber) {
        ticketQueue.stream()
                .filter(ticket -> ticket.getTicketNumber() == ticketNumber)
                .findFirst().ifPresent(ticket -> {
                    Client client = ticket.getClient();
                    if (client.getWashCount() > 100) client.setClientType(ClientType.VIP);
                    ticket.setWashStatus(WashStatus.FINISHED);
                    ticket.setFinishTime(LocalDateTime.now());
                });
    }

    public void cancelWash(long ticketNumber) {
        ticketQueue.stream()
                .filter(ticket -> ticket.getTicketNumber() == ticketNumber)
                .findFirst().ifPresent(ticket -> ticket.setWashStatus(WashStatus.CANCELLED));
    }

    public void CarWashPayment(long ticketNumber) {
        ticketQueue.stream()
                .filter(ticket -> ticket.getTicketNumber() == ticketNumber)
                .findFirst().ifPresent(ticket -> {
                    ticket.setWashStatus(WashStatus.PAID);
                    ticketQueue.remove(ticket);
                });
    }

    public void showTicketQueue() {
        ticketQueue.stream()
                .filter(ticket -> ticket.getWashStatus() == WashStatus.IN_PROGRESS
                        || ticket.getWashStatus() == WashStatus.NEW
                        || ticket.getWashStatus() == WashStatus.FINISHED)
                .forEach(ticket -> System.out.printf("%-10s %-8s %-15s %-30s %-10s %s%n",
                        ticket.getWashStatus(), ticket.getTicketNumber(), ticket.getClient().getName(),
                        ticket.getCar(), ticket.getClient().getClientType(),
                        ticket.getWashTypes().stream().map(WashType::name).collect(Collectors.joining(", "))));
    }

    public void resetWashCarNumber() {
        if (ticketQueue.isEmpty()) {
            ticketNumber = 0;
        } else System.out.println("There are active washes in the queue");
    }

    private void tryToUseDiscount(Ticket ticket) {
        Client client = ticket.getClient();
        if (client.getWashCount() % 10 == 0 && client.getWashCount() > 0) ticket.applyDiscount(10);
    }

    private void addTicketToQueue(Client client, Ticket ticket) {
        if (client.getClientType() == ClientType.VIP) {
            int vipCount = (int) ticketQueue.stream().filter(t -> t.getClient().getClientType() == ClientType.VIP).count();
            ticketQueue.add(vipCount, ticket);
        } else ticketQueue.add(ticket);
    }

    private void updateClientDataBase(Client client, Car car) {
        clientManager.addClient(client);
        if (!client.getCars().contains(car)) clientManager.addCarToClient(client, car);
    }
}