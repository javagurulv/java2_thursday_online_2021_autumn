package lv.javaguru.java2.oddJobs.core.response.find;

import lv.javaguru.java2.oddJobs.core.domain.Client;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import lv.javaguru.java2.oddJobs.core.response.CoreResponse;

import java.util.List;

public class FindClientsResponse extends CoreResponse {

    private List<Client> clients;

    public FindClientsResponse(List<Client> clients,List<CoreError> errors) {
        super(errors);
        this.clients = clients;
    }

    public List<Client> getClients() {
        return clients;
    }
}
