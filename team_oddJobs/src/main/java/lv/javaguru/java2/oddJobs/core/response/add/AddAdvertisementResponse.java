package lv.javaguru.java2.oddJobs.core.response.add;

import lv.javaguru.java2.oddJobs.core.response.CoreError;
import lv.javaguru.java2.oddJobs.core.response.CoreResponse;
import lv.javaguru.java2.oddJobs.core.domain.Advertisement;

import java.util.List;

public class AddAdvertisementResponse extends CoreResponse {

    private Advertisement advertisement;

    public AddAdvertisementResponse(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    public AddAdvertisementResponse(List<CoreError> errors){
        super(errors);
    }


    public Advertisement getAdvertisement() {
        return advertisement;
    }
}
