package lv.javaguru.java2.jg_entertainment.restaurant.core.database;

import lv.javaguru.java2.jg_entertainment.restaurant.core.services.Visitors;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ImplDatabaseRestaurant implements DatabaseVisitors {

    List<Visitors> clientInRestaurant = new ArrayList<>();
    private Long idClient = 1L;

    @Override
    public void saveClientToRestaurantList(Visitors clientInfo) {
        clientInfo.setIdClient(idClient);
        idClient++;
        clientInRestaurant.add(clientInfo);
    }

    @Override
    public List<Visitors> findVisitorsByNameAndTelephoneNumber(Long telephoneNumber, String nameVisitors) {
        return clientInRestaurant.stream()
                .filter(visitors -> visitors.getClientName().equals(nameVisitors))
                .filter(visitors -> visitors.getTelephoneNumber() == telephoneNumber)
                .collect(Collectors.toList());
    }

    @Override
    public List<Visitors> findClientById(Long idVisitors) {
        return clientInRestaurant.stream()
                .filter(visitors -> visitors.getIdClient().equals(idVisitors))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteClientWithNameAndId(Long id, String nameVisitor) {
        boolean visitorDeleteFromRestaurantList = false;
        Optional<Visitors> visitorsOptional = clientInRestaurant.stream()
                .filter(visitors -> visitors.getClientName().equals(nameVisitor) &&
                        visitors.getIdClient().equals(id))
                .findFirst();
        if (visitorsOptional.isPresent()) {
            Visitors visitors = visitorsOptional.get();
            visitorDeleteFromRestaurantList = clientInRestaurant.remove(visitors);
        }
        return visitorDeleteFromRestaurantList;
    }

    @Override
    public List<Visitors> showAllClientsInList() {
        return clientInRestaurant;
    }
}
//@Override
//    public void deleteClientWithId(Long id) {
//        clientInRestaurant.stream()
//                .filter(customer -> customer.getIdClient().equals(id))
//                .findFirst()
//                .ifPresent(customer -> clientInRestaurant.remove(customer));
//    }
//@Override
//public boolean findClientById(Long id)
///        for (Visitors visitor : clientInRestaurant) {
//            if (visitor.getIdClient().equals(id)) {
//                return true;
//            }
//        }
//        return false;