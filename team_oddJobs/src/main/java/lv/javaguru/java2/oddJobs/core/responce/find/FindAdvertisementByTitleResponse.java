package lv.javaguru.java2.oddJobs.core.responce.find;

import lv.javaguru.java2.oddJobs.domain.Advertisement;
import lv.javaguru.java2.oddJobs.core.responce.CoreError;
import lv.javaguru.java2.oddJobs.core.responce.CoreResponse;

import java.util.List;

public class FindAdvertisementByTitleResponse extends CoreResponse {

    private List<Advertisement> advertisements;

    public FindAdvertisementByTitleResponse(List<Advertisement> foundAdvertisement, List<CoreError> errors) {
        super(errors);
        this.advertisements = foundAdvertisement;
    }


    public List<Advertisement> getAdvertisements() {
        return advertisements;

//     public Advertisement getFoundAdvertisement() {
//         return foundAdvertisment;

    }
}
