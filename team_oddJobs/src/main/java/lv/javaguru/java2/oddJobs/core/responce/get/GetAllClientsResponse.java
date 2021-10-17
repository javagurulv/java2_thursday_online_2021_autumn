package lv.javaguru.java2.oddJobs.core.responce.get;

import lv.javaguru.java2.oddJobs.core.responce.CoreResponse;
import lv.javaguru.java2.oddJobs.domain.Client;

import java.util.List;

public class GetAllClientsResponse extends CoreResponse {

    private List<Client> clients;

    public GetAllClientsResponse(List<Client> clients) {
        this.clients = clients;
    }

    public List<Client> getClients() {
        return clients;
    }
}
