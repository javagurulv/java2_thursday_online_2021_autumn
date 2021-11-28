package lv.javaguru.java2.oddJobs.core.responce.add;

import lv.javaguru.java2.oddJobs.core.domain.Client;
import lv.javaguru.java2.oddJobs.core.responce.CoreError;
import lv.javaguru.java2.oddJobs.core.responce.CoreResponse;

import java.util.List;

public class AddClientResponse extends CoreResponse {

    private Client client;

    public AddClientResponse(Client client) {
        this.client = client;
    }

    public AddClientResponse(List<CoreError> errors) {
        super(errors);
    }


    public Client getClient() {
        return client;
    }
}
