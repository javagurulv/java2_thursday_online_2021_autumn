package lv.javaguru.java2.jg_entertainment.restaurant.core.database.user_repository;

import lv.javaguru.java2.jg_entertainment.restaurant.domain.Visitor;

import java.util.List;


public interface VisitorsRepository {

    void saveClientToRestaurantList(Visitor clientInfo);

    List<Visitor> findVisitorsByNameAndTelephoneNumber(String nameVisitors, String telephoneNumber);

    List<Visitor> findClientById(Long idVisitors);

    List<Visitor> findByNameVisitor(String nameVisitor);

    List<Visitor> findBySurnameVisitor(String surnameVisitor);

    List<Visitor> findByNameAndSurname (String nameVisitor, String surnameVisitor);

    boolean deleteClientWithIDAndName(Long id, String nameVisitors);

    List<Visitor> showAllClientsInList();

}
