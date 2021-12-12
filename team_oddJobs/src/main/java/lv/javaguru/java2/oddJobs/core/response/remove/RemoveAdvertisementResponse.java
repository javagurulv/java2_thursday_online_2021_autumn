package lv.javaguru.java2.oddJobs.core.response.remove;

import lv.javaguru.java2.oddJobs.core.response.CoreError;
import lv.javaguru.java2.oddJobs.core.response.CoreResponse;

import java.util.List;

public class RemoveAdvertisementResponse extends CoreResponse {

    private boolean advertisementRemoved;

    public RemoveAdvertisementResponse(boolean advertisementRemoved) {
        this.advertisementRemoved = advertisementRemoved;
    }

    public RemoveAdvertisementResponse(List<CoreError> errors) {

        super(errors);
    }

    public boolean isAdvertisementRemoved() {
        return advertisementRemoved;
    }

}

