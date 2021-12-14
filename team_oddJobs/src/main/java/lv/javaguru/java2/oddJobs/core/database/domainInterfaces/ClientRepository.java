package lv.javaguru.java2.oddJobs.core.database.domainInterfaces;

import lv.javaguru.java2.oddJobs.core.domain.Client;
import lv.javaguru.java2.oddJobs.core.domain.Specialist;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    void addClient(Client client);

    boolean removeClientById(Long clientId);

    boolean removeClient(Long clientId, String clientName, String clientSurname);

    List<Client> findClientsById(Long clientId);

    List<Client> findClientsByName(String clientName);

    List<Client> findClientBySurname(String clientSurname);
    List<Client> findClientByNameAndSurname(String clientName, String clientSurname);

    List<Client> findClientByIdAndNameAndSurname(Long clientId, String clientName, String clientSurname);

    List<Client> getAllClients();

    Optional<Client>getById(Long id);
}
