package database;

import service_visitors.Visitors;

import java.util.List;


public interface Database {

    void saveClientOfRestaurant(Visitors clientInfo);

    boolean findClientById(Long id);

    void deleteClientWithId(Long id);

    List<Visitors> showAllClientsInList();

    //void editReservation();

    //List<Visitors> showAllReservation();

}
