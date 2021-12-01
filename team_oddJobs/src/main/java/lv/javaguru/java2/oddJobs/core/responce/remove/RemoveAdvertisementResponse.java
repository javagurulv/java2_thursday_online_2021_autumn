package lv.javaguru.java2.oddJobs.core.responce.remove;

import lv.javaguru.java2.oddJobs.core.responce.CoreError;
import lv.javaguru.java2.oddJobs.core.responce.CoreResponse;

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

