package lv.javaguru.java2.oddJobs.core.response.update;

import lv.javaguru.java2.oddJobs.core.domain.Advertisement;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import lv.javaguru.java2.oddJobs.core.response.CoreResponse;

import java.util.List;

public class UpdateAdvertisementResponse extends CoreResponse {

    private Advertisement updatedAdvertisement;

    public UpdateAdvertisementResponse(List<CoreError> errors) {
        super(errors);
    }

    public UpdateAdvertisementResponse(Advertisement updatedAdvertisement) {
        this.updatedAdvertisement = updatedAdvertisement;
    }

    public Advertisement getUpdatedAdvertisement() {
        return updatedAdvertisement;
    }
}
