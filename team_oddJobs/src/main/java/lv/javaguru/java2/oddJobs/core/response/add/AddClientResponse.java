package lv.javaguru.java2.oddJobs.core.response.add;

import lv.javaguru.java2.oddJobs.core.domain.Client;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import lv.javaguru.java2.oddJobs.core.response.CoreResponse;

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
