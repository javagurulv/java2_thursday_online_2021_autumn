package database;

import service_visitors.Visitors;

import java.util.ArrayList;
import java.util.List;

public class ImplDatabaseRestaurant implements Database {

    List<Visitors> clientInRestaurant = new ArrayList<>();
    private Long idClient = 1L;

    @Override
    public void saveClientOfRestaurant(Visitors clientInfo) {
        clientInfo.setIdClient(idClient);
        idClient++;
        clientInRestaurant.add(clientInfo);
    }

    @Override
    public boolean findClientById(Long id) {
        for (Visitors visitor : clientInRestaurant) {
            if (visitor.getIdClient().equals(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteClientWithId(Long id) {
        clientInRestaurant.stream()
                .filter(customer -> customer.getIdClient().equals(id))
                .findFirst()
                .ifPresent(customer -> clientInRestaurant.remove(customer));
    }


    @Override
    public List<Visitors> showAllClientsInList() {
        return clientInRestaurant;
    }
}
