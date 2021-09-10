package lv.javaguru.java2.jg_entertainment.restaurant.core.database;

import lv.javaguru.java2.jg_entertainment.restaurant.core.services.Visitors;

import java.util.List;


public interface DatabaseVisitors {

    void saveClientToRestaurantList(Visitors clientInfo);

    List<Visitors> findVisitorsByNameAndTelephoneNumber(String nameVisitors, Long telephoneNumber);//

    List<Visitors> findClientById(Long idVisitors);

    boolean deleteClientWithNameAndId(String nameVisitors, Long id);

    List<Visitors> showAllClientsInList();


    //void editReservation();

    //List<Visitors> showAllReservation();

}
