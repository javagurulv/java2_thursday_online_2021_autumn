package lv.javaguru.java2.oddJobs.core.responce.add;

import lv.javaguru.java2.oddJobs.core.responce.CoreError;
import lv.javaguru.java2.oddJobs.core.responce.CoreResponse;
import lv.javaguru.java2.oddJobs.domain.Advertisement;

import java.util.List;

public class AddAdvertismentResponce extends CoreResponse {

    private Advertisement advertisement;

    public AddAdvertismentResponce(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    public AddAdvertismentResponce(List<CoreError> errors){
        super(errors);
    }


    public Advertisement getAdvertisement() {
        return advertisement;
    }
}
