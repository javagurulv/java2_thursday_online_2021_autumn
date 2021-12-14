package lv.javaguru.java2.oddJobs.core.response.remove;

import lv.javaguru.java2.oddJobs.core.domain.Client;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import lv.javaguru.java2.oddJobs.core.response.CoreResponse;

import java.util.List;

public class DeleteClientResponse extends CoreResponse {
    private Client deletedClient;

    public DeleteClientResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeleteClientResponse(Client deletedClient) {
        this.deletedClient = deletedClient;
    }

}
