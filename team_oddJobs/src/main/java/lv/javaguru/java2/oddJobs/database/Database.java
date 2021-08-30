package lv.javaguru.java2.oddJobs.database;

import lv.javaguru.java2.oddJobs.Client;
import lv.javaguru.java2.oddJobs.Specialist;

import java.util.List;

public interface Database {

    void saveSpecialist(Specialist specialist);

    void saveClient(Client client);

    void deleteSpecialistById(Long id);

    void deleteClientById(Long id);

    void findSpecialistByProfession(String profession);

    List<Specialist> getAllSpecialist();

    List<Client> getAllClients();


}
