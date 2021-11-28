package lv.javaguru.java2.oddJobs.core.responce.add;

import lv.javaguru.java2.oddJobs.core.responce.CoreError;
import lv.javaguru.java2.oddJobs.core.responce.CoreResponse;
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
