package lv.javaguru.java2.oddJobs.core.response.remove;

import lv.javaguru.java2.oddJobs.core.domain.Advertisement;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import lv.javaguru.java2.oddJobs.core.response.CoreResponse;

import java.util.List;

public class DeleteAdvertisementResponse extends CoreResponse {
    private Advertisement deletedClientResponse;

    public DeleteAdvertisementResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeleteAdvertisementResponse(Advertisement deletedClientResponse) {
        this.deletedClientResponse = deletedClientResponse;
    }
}
