package lv.javaguru.java2.oddJobs.core.database.unUsed;


import lv.javaguru.java2.oddJobs.core.domain.Advertisement;
import lv.javaguru.java2.oddJobs.core.domain.Client;
import lv.javaguru.java2.oddJobs.core.domain.Specialist;

import java.util.List;

public interface Database {

    void addSpecialist(Specialist specialist);

    void addClient(Client client);

    boolean removeClientById(Long clientId);

    boolean removeSpecialistById(Long specialistId);

    boolean removeSpecialist(Long specialistId, String specialistName, String specialistSurname);

    boolean removeClient(Long clientId, String clientName, String clientSurname);


    List<Client> findClientsById(Long clientId);

    List<Client> findClientsByName(String clientName);

    List<Client> findClientBySurname(String clientSurname);

    List<Client> findClientByIdAndNameAndSurname(Long clientId, String clientName, String clientSurname);

    List<Specialist> findSpecialistById(Long specialistId);

    List<Specialist> findSpecialistByName(String specialistName);

    List<Specialist> findSpecialistBySurname(String specialistSurname);

    List<Specialist> findSpecialistByProfession(String specialistProfession);

    List<Specialist> findSpecialistByNameAndSurnameAndProfession(String specialistName, String specialistSurname, String specialistProfession);

    void addAdvertisement(Advertisement advBoard);

    boolean removeAdvertisement(Long advId, String advBoardTitle);

    List<Advertisement> findAdvertisementByTitle(String advTitle);


    List<Advertisement> findAdvertisementById(long advId);

//    void findAdvertisementById(long advId);


    List<Specialist> getAllSpecialist();

    List<Client> getAllClients();

    List<Advertisement> getAllAdvertisement();

}
