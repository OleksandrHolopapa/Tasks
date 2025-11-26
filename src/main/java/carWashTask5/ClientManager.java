package carWashTask5;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientManager {
    private final List<Client> clients = new ArrayList<>();

    public boolean addClient(Client client) {
        if (isClientExist(client)) {
            return false;
        }
        clients.add(client);
        return true;
    }

    public Optional<Client> getClientByPhoneNumber(String phoneNumber) {
        return clients.stream()
                .filter(client -> client.getPhone().equals(phoneNumber))
                .findFirst();
    }

    public void removeClient(Client client) {
        clients.remove(client);
    }

    public void addCarToClient(Client client, Car car) {
        if (isClientExist(client) && car != null) {
            client.getCars().add(car);
        } else {
            System.out.println("This client is not exist" + client);
        }
    }

    public void removeCarFromClient(Client client, Car car) {
        if (isClientExist(client)) {
            client.getCars().remove(car);
        } else {
            System.out.println("This client is not exist");
        }
    }

    private boolean isClientExist(Client client) {
        return clients.contains(client) && client != null;
    }
}