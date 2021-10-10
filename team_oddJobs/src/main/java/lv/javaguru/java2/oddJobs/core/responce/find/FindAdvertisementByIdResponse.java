package lv.javaguru.java2.oddJobs.core.responce.find;

import lv.javaguru.java2.oddJobs.domain.Advertisement;
import lv.javaguru.java2.oddJobs.core.responce.CoreError;
import lv.javaguru.java2.oddJobs.core.responce.CoreResponse;

import java.util.List;

public class FindAdvertisementByIdResponse extends CoreResponse {

    private List<Advertisement> foundAdvertisements;

    public FindAdvertisementByIdResponse(List<Advertisement> foundAdvertisements, List<CoreError> errors) {
        super(errors);
        this.foundAdvertisements = foundAdvertisements;
    }

    public List<Advertisement> getFoundAdvertisements() {return foundAdvertisements;}

// public class FindAdvertisementByIdResponse {

//     private Advertisement foundAdvertisement;

//     public FindAdvertisementByIdResponse(Advertisement foundAdvertisement) {
//         this.foundAdvertisement = foundAdvertisement;
//     }

//     public Advertisement getFoundAdvertisement() {return foundAdvertisement;}

}
