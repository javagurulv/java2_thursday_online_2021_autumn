package lv.javaguru.java2.oddJobs.core.response.update;

import lv.javaguru.java2.oddJobs.core.domain.Client;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import lv.javaguru.java2.oddJobs.core.response.CoreResponse;

import java.util.List;

public class UpdateClientResponse extends CoreResponse {

    private Client updatedClient;

    public UpdateClientResponse(List<CoreError> errors) {
        super(errors);
    }

    public UpdateClientResponse(Client updatedClient) {
        this.updatedClient = updatedClient;
    }

    public Client getUpdatedClient() {
        return updatedClient;
    }
}
