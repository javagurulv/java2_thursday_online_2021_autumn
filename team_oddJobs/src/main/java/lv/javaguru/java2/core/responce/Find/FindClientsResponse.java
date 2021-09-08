package lv.javaguru.java2.core.responce.Find;

import lv.javaguru.java2.Client;
import lv.javaguru.java2.core.responce.CoreError;
import lv.javaguru.java2.core.responce.CoreResponse;

import java.util.List;

public class FindClientsResponse extends CoreResponse {

    private List<Client> clients;

    public FindClientsResponse(List<Client> clients,List<CoreError> errors) {
        super(errors);
        this.clients = clients;
    }

    public List<Client> getClient() {
        return clients;
    }
}
