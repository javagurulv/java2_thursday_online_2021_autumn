package lv.javaguru.java2.oddJobs.core.response.get;

import lv.javaguru.java2.oddJobs.core.domain.Advertisement;
import lv.javaguru.java2.oddJobs.core.response.CoreError;
import lv.javaguru.java2.oddJobs.core.response.CoreResponse;

import java.util.List;

public class GetAdvertisementResponse extends CoreResponse {

    private Advertisement advertisement;

    public GetAdvertisementResponse(List<CoreError> errors) {
        super(errors);
    }

    public GetAdvertisementResponse(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }
}
