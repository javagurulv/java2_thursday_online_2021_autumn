package lv.javaguru.java2.oddJobs.core.services.get;

import lv.javaguru.java2.oddJobs.core.requests.get.GetAllClientsRequest;
import lv.javaguru.java2.oddJobs.core.responce.get.GetAllClientsResponse;
import lv.javaguru.java2.oddJobs.database.domainInterfaces.ClientRepository;
import lv.javaguru.java2.oddJobs.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Component
@Transactional
public class GetAllClientsService {

    @Autowired
    private ClientRepository clientRepository;

    public GetAllClientsResponse execute (GetAllClientsRequest request) {
        List<Client> clients = clientRepository.getAllClients();
        return new GetAllClientsResponse(clients);
    }
}
