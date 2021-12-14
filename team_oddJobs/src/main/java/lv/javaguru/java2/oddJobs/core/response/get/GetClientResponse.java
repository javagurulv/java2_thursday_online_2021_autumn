package lv.javaguru.java2.oddJobs.core.response.get;

import lv.javaguru.java2.oddJobs.core.domain.Client;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import lv.javaguru.java2.oddJobs.core.response.CoreResponse;

import java.util.List;

public class GetClientResponse extends CoreResponse {

    private Client client;

    public GetClientResponse(List<CoreError> errors) {
        super(errors);
    }

    public GetClientResponse(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }
}
