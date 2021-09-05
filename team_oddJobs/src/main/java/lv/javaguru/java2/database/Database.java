package lv.javaguru.java2.database;


import lv.javaguru.java2.Advertisement;
import lv.javaguru.java2.Client;
import lv.javaguru.java2.Specialist;

import java.util.List;

public interface Database {

    void addSpecialist(Specialist specialist);

    void addClient(Client client);

    boolean removeSpecialist(Long id, String name, String surName);

    boolean removeClient(Long id, String name, String surName);

    void findSpecialistByProfession(String profession);

    void findClientById(Long id);
    void findClientByName(String name);
    void findClientBySurname(String surName);

    void addAdvertisement(Advertisement advBoard);
    boolean removeAdvertisement(Long advId, String advBoardTitle);

//    void findClientBySearchCriteria(FindClientByIdService findClientBySearchCriteria);
//    void findSpecialistBySearchCriteria (FindSpecialistBySearchCriteriaService findSpecialistBySearchCriteriaService)

    List<Specialist> getAllSpecialist();

    List<Client> getAllClients();


}
