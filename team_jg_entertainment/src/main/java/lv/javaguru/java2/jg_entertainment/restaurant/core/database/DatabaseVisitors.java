package lv.javaguru.java2.jg_entertainment.restaurant.core.database;

import lv.javaguru.java2.jg_entertainment.restaurant.core.services.Visitors;

import java.util.List;


public interface DatabaseVisitors {

    void saveClientToRestaurantList(Visitors clientInfo);

    List<Visitors> findVisitorsByNameAndTelephoneNumber(Long telephoneNumber, String nameVisitors);

    List<Visitors> findClientById(Long idVisitors);

    boolean deleteClientWithIDAndName(Long id, String nameVisitors);

    List<Visitors> showAllClientsInList();


    //void editReservation();

    //List<Visitors> showAllReservation();

}
