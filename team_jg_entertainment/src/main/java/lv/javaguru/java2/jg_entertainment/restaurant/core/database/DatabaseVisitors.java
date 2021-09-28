package lv.javaguru.java2.jg_entertainment.restaurant.core.database;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitors;

import java.util.List;


public interface DatabaseVisitors {

    void saveClientToRestaurantList(Visitors clientInfo);

    List<Visitors> findVisitorsByNameAndTelephoneNumber(String nameVisitors, Long telephoneNumber);

    List<Visitors> findClientById(Long idVisitors);

    List<Visitors> findByNameVisitor(String nameVisitor);

    List<Visitors> findBySurnameVisitor(String surnameVisitor);

    List<Visitors> findByNameAndSurname (String nameVisitor, String surnameVisitor);

    boolean deleteClientWithIDAndName(Long id, String nameVisitors);

    List<Visitors> showAllClientsInList();

}
