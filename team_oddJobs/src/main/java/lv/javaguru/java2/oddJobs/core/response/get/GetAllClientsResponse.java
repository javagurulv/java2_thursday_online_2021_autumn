package lv.javaguru.java2.oddJobs.core.response.get;

import lv.javaguru.java2.oddJobs.core.response.CoreResponse;
import lv.javaguru.java2.oddJobs.core.domain.Client;

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
