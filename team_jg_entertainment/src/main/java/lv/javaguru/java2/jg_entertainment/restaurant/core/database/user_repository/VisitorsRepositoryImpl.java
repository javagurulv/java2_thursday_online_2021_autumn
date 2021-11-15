package lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

//@Component
public class VisitorsRepositoryImpl implements VisitorsRepository {

    List<Visitor> clientInRestaurant = new ArrayList<>();
    private Long idClient = 1L;

    @Override
    public void saveClientToRestaurantList(Visitor clientInfo) {
        clientInfo.setIdClient(idClient);
        idClient++;
        clientInRestaurant.add(clientInfo);
    }

    @Override
    public List<Visitor> findVisitorsByNameAndTelephoneNumber(String nameVisitors, String telephoneNumber) {
        return clientInRestaurant.stream()
                .filter(visitors -> visitors.getClientName().equals(nameVisitors))
                .filter(visitors -> visitors.getTelephoneNumber().equals(telephoneNumber))
                .collect(Collectors.toList());
    }

    @Override
    public List<Visitor> findClientById(Long idVisitors) {
        return clientInRestaurant.stream()
                .filter(visitors -> visitors.getIdClient().equals(idVisitors))
                .collect(Collectors.toList());
    }

    @Override
    public List<Visitor> findByNameVisitor(String nameVisitor) {
        return clientInRestaurant.stream()
                .filter(visitors -> visitors.getClientName().equals(nameVisitor))
                .collect(Collectors.toList());
    }

    @Override
    public List<Visitor> findBySurnameVisitor(String surnameVisitor) {
        return clientInRestaurant.stream()
                .filter(visitors -> visitors.getSurname().equals(surnameVisitor))
                .collect(Collectors.toList());
    }

    @Override
    public List<Visitor> findByNameAndSurname(String name, String surname) {
        return clientInRestaurant.stream()
                .filter(visitors -> visitors.getClientName().equals(name))
                .filter(visitors -> visitors.getSurname().equals(surname))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteClientWithIDAndName(Long id, String nameVisitor) {
        boolean visitorDeleteFromRestaurantList = false;
        Optional<Visitor> visitorsOptional = clientInRestaurant.stream()
                .filter(visitors -> visitors.getClientName().equals(nameVisitor) &&
                        visitors.getIdClient().equals(id))
                .findFirst();
        if (visitorsOptional.isPresent()) {
            Visitor visitor = visitorsOptional.get();
            visitorDeleteFromRestaurantList = clientInRestaurant.remove(visitor);
        }
        return visitorDeleteFromRestaurantList;
    }

    @Override
    public List<Visitor> showAllClientsInList() {
        return clientInRestaurant.stream()
                .sorted(Comparator.comparing(Visitor::getClientName).thenComparing(Visitor::getSurname))
                .collect(toList());
    }
}

//@Override
//    public void deleteClientWithId(Long id) {
//        clientInRestaurant.stream()
//                .filter(customer -> customer.getIdClient().equals(id))
//                .findFirst()
//                .ifPresent(customer -> clientInRestaurant.remove(customer));
//}