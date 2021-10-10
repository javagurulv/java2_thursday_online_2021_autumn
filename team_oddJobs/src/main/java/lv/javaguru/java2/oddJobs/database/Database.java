package lv.javaguru.java2.oddJobs.database;


import lv.javaguru.java2.oddJobs.domain.Advertisement;
import lv.javaguru.java2.oddJobs.domain.Client;
import lv.javaguru.java2.oddJobs.domain.Specialist;

import java.util.List;

public interface Database {

    void addSpecialist(Specialist specialist);

    void addClient(Client client);

    boolean removeSpecialist(Long id, String name, String surName);

    boolean removeClient(Long id, String name, String surName);


    List<Client> findClientsById(Long clientId);

    List<Client> findClientsByName(String clientName);

    List<Client> findClientBySurname(String clientSurname);

    List<Client> findClientByIdAndNameAndSurname(Long id, String clientName, String clientSurname);

    List<Specialist> findSpecialistById(Long specialistId);

    List<Specialist> findSpecialistByName(String specialistName);

    List<Specialist> findSpecialistBySurname(String specialistSurname);

    List<Specialist> findSpecialistByProfession(String profession);

    List<Specialist> findSpecialistByNameAndSurnameAndProfession(String specialistName, String specialistSurname, String specialistProfession);

    void addAdvertisement(Advertisement advBoard);

    boolean removeAdvertisement(Long advId, String advBoardTitle);

    List<Advertisement> findAdvertisementByTitle(String advTitle);


    List<Advertisement> findAdvertisementById(long advId);

//    void findAdvertisementById(long advId);


    List<Specialist> getAllSpecialist();

    List<Client> getAllClients();

    List<Advertisement> getAllAdvertisemets();

}
